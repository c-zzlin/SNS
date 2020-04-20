package com.clim.blog.service.impl;

import com.clim.blog.dao.BlogDao;
import com.clim.blog.model.dto.LikeDto;
import com.clim.blog.model.dto.MsgDto;
import com.clim.blog.model.entity.*;
import com.clim.blog.model.enums.LikeCode;
import com.clim.blog.model.vo.BlogVo;
import com.clim.blog.service.BlogService;
import com.clim.provider.service.WsPushApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    BlogDao blogDao;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    WsPushApi wsPushApi;
    @Override
    public int insert_img(List<FriendCircleImg> list) {
       return  blogDao.insert_img(list);
    }
    public String getOrderIdPrefix(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //补两位,因为一年最多三位数
        String monthFormat = String.format("%1$02d", month+1);
        //补两位，因为日最多两位数
        String dayFormat = String.format("%1$02d", day);
        //补两位，因为小时最多两位数
        String hourFormat = String.format("%1$02d", hour);
        return year + monthFormat + dayFormat+hourFormat;
    }

    /**
     * 生成订单
     * @param prefix
     * @return
     */
    public Long orderId(String prefix) {
        logger.info("===prefix= "+prefix);
        String key = "DEMO_ORDER_ID_" + prefix;
        String orderId = null;
        logger.info(redisTemplate.toString());
        redisTemplate.opsForValue().get("WSmain:1");
        try {
            logger.info("======key =="+key);
            Long increment = redisTemplate.opsForValue().increment(key,1);
            //往前补6位
            orderId=prefix+String.format("%1$06d",increment);
        } catch (Exception e) {
            System.out.println("生成订单号失败");
            e.printStackTrace();
        }
        return Long.valueOf(orderId);
    }

    public  String getKey(){
        String orderIdPrefix = getOrderIdPrefix(new Date());
        Long aLong = orderId(orderIdPrefix);
        return String.valueOf(aLong);
    }

    @Override
    public int send(MsgDto msgDto) {
        int res = blogDao.send(
                msgDto.getMsg_id(),
                msgDto.getUser_id(),
                msgDto.getContent()
        );
        logger.info("===res: "+res);
        if(res>0){
            blogDao.insert_timeline(
                    msgDto.getMsg_id(),
                    msgDto.getUser_id(),
                    1
            );
            List<String> user_list = find_friendId(msgDto.getUser_id());
            List<CommentId> list = new ArrayList<>();
            for(int i=0;i<user_list.size();i++){
                list.add(new CommentId(
                        msgDto.getMsg_id(),
                        user_list.get(i)
                ));
            }
            if(list!=null&&list.size()>0)
            blogDao.insert_friend_timeline(list);

            for(String user_id:user_list){
                logger.info(user_id);
                logger.info(stringRedisTemplate.hasKey("WSmain:"+user_id).toString());
                if(stringRedisTemplate.hasKey("WSmain:"+user_id)){
                    logger.info("push_wsmain:"+user_id);
                wsPushApi.friendPush(user_id,"home");
                }
            }
        }
        return res;
    }

    /**
     *
     *  * 查询好友列表，查timeline表，
     *  *     根据msg_id查user_id 跟图片，
     *  TODO  差下面两步
     *  *     点赞表查出所有user_id，跟好友列表进行去重，并判断自身是否点赞
     *  *     查出评论表\回复表user_id，去重，统计
     *
     * @param user_id
     * @return
     */
    @Override
    public List<BlogVo> search_blog(String user_id) {
        List<String> msg_id_list = find_msgId(user_id);
        List<String> friend_id_list = find_friendId(user_id);
        logger.info("msg_id_list:size()  :"+msg_id_list.size());
        if(msg_id_list == null || msg_id_list.size() == 0){
            return null;
        }
        List<BlogVo> blog_list = blogDao.find_blog(msg_id_list);
        logger.info("======查完全部msg_id======");
        List<BlogLike> like_list = blogDao.find_all_like(msg_id_list);
        Set<String> set=new HashSet<>(friend_id_list);
        List<CommentId> comment_list = blogDao.find_all_comment(msg_id_list);
        List<ReplyId> reply_list = blogDao.find_all_reply(msg_id_list);
        like_help(set, like_list, blog_list, user_id);
        comment_help(set, comment_list, blog_list, user_id);    //统计评论的数量
        reply_help(set, reply_list, blog_list, user_id);      //统计回复的数量
        return blog_list;
    }

    private void reply_help(Set<String> set, List<ReplyId> reply_list, List<BlogVo> blog_list, String user_id) {
        int j=0;
        for(int i=0;i<blog_list.size()&&j<reply_list.size();i++){
            int count = blog_list.get(i).getComment();
            String blog_msgId = blog_list.get(i).getMsg_id();
            if(blog_msgId.equals(reply_list.get(j).getMsg_id())){
                for(;j<reply_list.size()&&
                        blog_msgId.equals(reply_list.get(j).getMsg_id());j++){
                    ReplyId temp=reply_list.get(j);
                    if((temp.getUser_id().equals(user_id) && set.contains(temp.getReply_id()))||
                            (temp.getReply_id().equals(user_id)&& set.contains(temp.getUser_id()))||
                            (set.contains(temp.getUser_id())&& set.contains((temp.getReply_id())))||
                            (temp.getReply_id().equals(user_id)&&temp.getUser_id().equals(user_id)))
                    {
                        count++;
                    }
                }
                blog_list.get(i).setComment(count);
            }
        }

    }

    /**
     * 计算一条动态中共同好友的评论数量
     * @param set
     * @param comment_list
     * @param blog_list
     * @param user_id
     */
    private void comment_help(Set<String> set, List<CommentId> comment_list,
                               List<BlogVo> blog_list,
                              String user_id) {
        int j=0;
        for(int i=0;i<blog_list.size()&&j<comment_list.size();i++){
            int count = blog_list.get(i).getComment();
            String blog_msgId = blog_list.get(i).getMsg_id();
            if(blog_msgId.equals(comment_list.get(j).getMsg_id())){
                for(;j<comment_list.size()&&
                        blog_msgId.equals(comment_list.get(j).getMsg_id());j++){
                    if(set.contains(comment_list.get(j).getUser_id())||comment_list.get(j).getUser_id().equals(user_id)){
                        count++;
                    }
                }
                blog_list.get(i).setComment(count);
            }
        }


    }


    private void like_help(Set<String> set,List<BlogLike> like_list, List<BlogVo> blog_list,String user_id){
        int j=0;
        for(int i=0; i<blog_list.size()&&j<like_list.size(); i++){
            //String userId = blog_list.get(i).getUser_id();  //用来确认自己是否点赞，is_like
            if(blog_list.get(i).getMsg_id().equals(like_list.get(j).getMsg_id())){    //可能有没人点赞的情况
                List<String> temp = like_list.get(j).getUser_id();
                int count=0;
                for(int k=0;k<temp.size();k++){
                    if(temp.get(k).equals(user_id)){
                        logger.info("自己点赞"+blog_list.get(i).getMsg_id());
                        blog_list.get(i).setIs_like(1);
                        count++;
                    }else if(set.contains(temp.get(k))){
                        count++;
                    }
                }
                blog_list.get(i).setLike(count);
                j++;
            }
        }
    }
    @Override
    public List<BlogLike> test(String user_id) {
        List<String> msg_id_list = find_msgId(user_id);

        return blogDao.find_all_like(msg_id_list);
    }

    @Override
    public void like(LikeDto likeDto) {
        if(likeDto.getIs_like() == LikeCode.IS_LIKE){
                    blogDao.cancel_like(
                            likeDto.getMsg_id(),
                            likeDto.getUser_id()
                    );
        }else if(likeDto.getIs_like() == LikeCode.NOT_LIKE){
                    blogDao.like(
                            likeDto.getMsg_id(),
                            likeDto.getUser_id()
                    );
                    if(stringRedisTemplate.hasKey("WSmain:"+likeDto.getFriend_id())){
                        wsPushApi.friendPush(
                                likeDto.getFriend_id(),
                                "LIKE"
                        );
                    }
        }
    }


    private List<String> find_msgId(String user_id){
        try {
            List<String> list = blogDao.find_all_msgId(user_id);
            return list;
        }catch (Exception e){
            logger.info("查询msgId出错");
            return null;
        }
    }
    /**
     * 找出所有好友ID
     * @param user_id
     * @return
     */
    private List<String> find_friendId(String user_id){
        try {
            List<String> list = blogDao.find_all_friend(user_id);
            return list;
        }catch (Exception e){
            logger.info("查询friendID出错");
            return null;
        }
    }
}
