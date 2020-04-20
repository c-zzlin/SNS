package com.clim.user.service.impl;

import com.clim.user.dao.UserDao;
import com.clim.user.model.vo.UserLoginVo;
import com.clim.user.service.UserService;
import com.clim.user.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserDao userDao;

    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public UserLoginVo login(String user_logon, String user_password) {
        User user=userDao.login(user_logon, user_password);
        if(user == null) return null;
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token,user.getUser_id());
        UserLoginVo userLoginVo = new UserLoginVo(
                user.getUser_id(),
                user.getUser_aiais(),
                user.getUser_image(),
                token
        );

        return userLoginVo;
    }

    @Override
    public boolean logout(String token) {
        logger.info("token是：     "+token);
        if(redisTemplate.hasKey(token)){
            logger.info("=======有这个账号在redis========");
            redisTemplate.delete(token);
            return true;
        }
        logger.info("=======退出失败======");
        return false;

    }

}
