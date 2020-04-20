package com.clim.chat.service.impl;

import com.clim.chat.dao.ChatDao;
import com.clim.chat.model.dto.LastMsg;
import com.clim.chat.model.dto.User;
import com.clim.chat.model.vo.FriendListVo;
import com.clim.chat.model.vo.RecordVo;
import com.clim.chat.service.ChatService;

import com.clim.provider.model.SendMsgDto;
import com.clim.provider.service.WsPushMsgApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ChatDao chatDao;
    @Autowired
    WsPushMsgApi wsPushMsgApi;
    @Autowired
    StringRedisTemplate redisTemplate;
    private String PRE_KEY  = "WSpm:";
    @Override
    public List<String> query_friendId(String user_id) {
        return chatDao.query_friendId(user_id);
    }

    @Override
    public List<FriendListVo> friendList(String user_id) {
        logger.info("=========friendList======= user_id:"+user_id);
        List<String> list_id = query_friendId(user_id);            //查出所有好友的ID
        List<User> user_list = chatDao.query_friend_user(list_id);
        Map<String ,User> map =new HashMap<>();
        for(int i=0;i<user_list.size();i++){
            map.put(user_list.get(i).getUser_id(),user_list.get(i));
        }
        List<FriendListVo> res = new ArrayList<>();
        for(int i=0;i<list_id.size();i++){
            LastMsg lastMsg = chatDao.query_friend_lastmsg(user_id,list_id.get(i));
            int sum = chatDao.un_read_count(list_id.get(i), user_id);
            FriendListVo friendListVo=new FriendListVo(
                    map.get(list_id.get(i)),
                    lastMsg,
                    sum);
            res.add(friendListVo);

        }

        logger.info("=====service结=====");
        return res;
    }

    @Override
    public List<RecordVo> record(String user_id, String reply_id) {

        List<RecordVo> res= chatDao.record(user_id, reply_id);
        chatDao.unread(user_id, reply_id);
        return res;
    }

    @Override
    public int send(SendMsgDto sendMsgDto) {
        //此服务添加数据库消息，并且根据redis是否在线进行推送，现在先测试推送
        int add = chatDao.send(
                sendMsgDto.getContent(),
                sendMsgDto.getTo(),
                sendMsgDto.getFrom()
        );
        String key = PRE_KEY+sendMsgDto.getFrom();
        if(redisTemplate.hasKey(key)){
            wsPushMsgApi.friendPush(sendMsgDto);
            logger.info("push成功");
        }

        return add;
    }

    @Override
    public int urcount(String user_id) {
        return chatDao.urcount(user_id);
    }
}
