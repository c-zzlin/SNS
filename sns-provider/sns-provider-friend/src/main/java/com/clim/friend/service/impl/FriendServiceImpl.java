package com.clim.friend.service.impl;

import com.clim.common.util.WsUtil;
import com.clim.friend.dao.FriendDao;
import com.clim.friend.model.dto.TimeLineDto;
import com.clim.friend.model.enums.Code;
import com.clim.friend.model.vo.FriendListVo;
import com.clim.friend.service.FriendService;
import com.clim.friend.service.ImPushService;
import com.clim.provider.service.WsPushApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendDao friendDao;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    WsPushApi wsPushApi;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<FriendListVo> getFriendList(String user_id) {

        List<FriendListVo> list = friendDao.getFriendList(user_id);
        return list;
    }

    @Override
    public List<FriendListVo> find(String user_aiais,String user_id) {
        List<FriendListVo> list = friendDao.find(user_aiais,user_id);
        return list;
    }

    @Override
    public int add(String user_id, String friend_id) {


        if(redisTemplate.hasKey("WSmain:"+friend_id)) {
            String isOnLine = redisTemplate.opsForValue().get("WSmain:"+friend_id);
            logger.info("======isonlone:  "+isOnLine);
            //调用websocket 推送
            if(WsUtil.is_main(isOnLine)){
                //推送
                logger.info("========开始push==========");
                int res = friendDao.add(user_id, friend_id, Code.NOADD, Code.ONLINE);
                wsPushApi.friendPush(friend_id, "FRIEND");
                logger.info("=========push成功=========");
                return res;
            }
        }
        logger.info("==============没有Push，对方不在线=============");
        int res = friendDao.add(user_id, friend_id, Code.NOADD, Code.OFFLINE);
        return res;
    }

    @Override
    public List<FriendListVo> commit(String user_id) {
        List<FriendListVo> list = friendDao.commit(user_id);
        return list;
    }

    @Override
    public int unread(String uesr_id) {
        return friendDao.unread(uesr_id);
    }

    @Override
    public int confirm(String user_id, String friend_id) {
        //确认通过申请，1更新好友status，2插入自身好友列表

        int res = friendDao.confirm(user_id, friend_id);
        int add = friendDao.add(user_id, friend_id, Code.ADD, Code.ONLINE);
        update_friend_timeline(user_id, friend_id);
        if(redisTemplate.hasKey("WSmain:"+friend_id)) {
            String isOnLine = redisTemplate.opsForValue().get("WSmain:"+friend_id);
            logger.info("======isonlone:  "+isOnLine);
            //调用websocket 推送
            if(WsUtil.is_main(isOnLine)){
                //推送
                logger.info("========开始push==========");
                wsPushApi.friendPush(friend_id, "PERSON");
                logger.info("=========push成功=========");
                return res;
            }
        }


        return res+add;
    }

    @Override
    public int delete(String user_id, String friend_id) {
        //根据User_id搜索 List<atten_id> => 查atten_id的user   查userid attenid的last msg，查count noread

        int i= friendDao.delete(user_id, friend_id);
        if(i>0){
            List<String> list = friendDao.friend_timeline_list(user_id);
            if(list!=null&&list.size()>0){
            friendDao.delete_All_timeline(list, friend_id);
            }
            list = friendDao.friend_timeline_list(friend_id);
            if(list!=null&&list.size()>0) {
                friendDao.delete_All_timeline(list, user_id);
            }
        }
        return i;
    }


    private void update_friend_timeline(String user_id,String friend_id){
           update_friend_timeline_helper(user_id, friend_id);
           update_friend_timeline_helper(friend_id, user_id);
    }

    private void update_friend_timeline_helper(String user_id,String friend_id){
        List<String> list = friendDao.friend_timeline_list(user_id);
        List<TimeLineDto> insert_list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            TimeLineDto timeLineDto = new TimeLineDto(friend_id,list.get(i));
            insert_list.add(timeLineDto);
        }
        if(insert_list!=null&&insert_list.size()>0)
        friendDao.insert_friend_timeline(insert_list);
    }

}
