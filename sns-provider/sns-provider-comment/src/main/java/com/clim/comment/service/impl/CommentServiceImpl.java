package com.clim.comment.service.impl;

import com.clim.comment.dao.CommentDao;
import com.clim.comment.model.dto.AddComDto;
import com.clim.comment.model.dto.CommentIdDto;
import com.clim.comment.model.dto.ReplyDto;
import com.clim.comment.model.entity.CommentBean;
import com.clim.comment.model.entity.ReplyBean;
import com.clim.comment.model.vo.CommentVo;
import com.clim.comment.model.vo.ReplyVo;
import com.clim.comment.service.CommentService;
import com.clim.provider.service.WsPushApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    WsPushApi wsPushApi;
    @Override
    public List<CommentVo> list(CommentIdDto commentIdDto) {
        List<String> friend_list = commentDao.query_friend_list(commentIdDto.getUser_id());
        List<CommentVo> list = commentDao.list(commentIdDto.getMsg_id());
        Set<String> set =new HashSet<>(friend_list);
        list = get_Mutual_friend(list,set,commentIdDto.getUser_id());
        return list;
    }

    @Override
    public BigInteger insert(AddComDto addComDto) {
        CommentBean commentBean=new CommentBean( addComDto.getContent(),
                addComDto.getUser_id(),
                addComDto.getMsg_id());
         commentDao.insert(commentBean);
         if(!addComDto.getFriend_id().equals(addComDto.getUser_id()) &&
                 stringRedisTemplate.hasKey("WSmain:"+addComDto.getFriend_id())){
             wsPushApi.friendPush(addComDto.getFriend_id(),
                     "content");
         }
         return commentBean.getComment_id();
    }

    @Override
    public BigInteger reply(ReplyDto replyDto) {
        ReplyBean replyBean = new ReplyBean(
                replyDto.getReply_id(),
                replyDto.getMsg_id(),
                replyDto.getUser_id(),
                replyDto.getContent(),
                replyDto.getComment_id());
        commentDao.reply(replyBean);
        if(stringRedisTemplate.hasKey("WSmain:"+replyDto.getReply_id())){
            wsPushApi.friendPush(replyDto.getReply_id(),
                    "content");
        }
        return replyBean.getComment_id();
    }

    /**
     * 删掉不是共同好友的评论
     * @param list
     * @param set
     * @param user_id
     */
    private List<CommentVo> get_Mutual_friend(List<CommentVo> list, Set<String> set, String user_id) {
        List<CommentVo> res = new ArrayList<>();
        for(int i=0 ; i<list.size(); i++){
            CommentVo commentVo = list.get(i);
            if(commentVo.getUser_id().equals(user_id)|| set.contains(commentVo.getUser_id())){
                List<ReplyVo> replyVo_list = commentVo.getList();
                List<ReplyVo> temp = new ArrayList<>();
                for(int j=0;j<replyVo_list.size();j++){
                    //String replyId =replyVo_list.get(j).getReply_id();
                    String userId = replyVo_list.get(j).getUser_id();
                    if(
                            userId.equals(user_id)||
                            set.contains(userId)){
                        temp.add(replyVo_list.get(j));
                    }
                }
                commentVo.setList(temp);
                res.add(commentVo);

            }
        }
        return res;
    }
}
