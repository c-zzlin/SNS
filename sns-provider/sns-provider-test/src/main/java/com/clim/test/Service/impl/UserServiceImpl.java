package com.clim.test.Service.impl;

import com.clim.test.Service.UserService;
import com.clim.test.dao.UserDao;
import com.clim.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public User selectUser() {
        User user=userDao.selectAllUser("1");
        System.out.println(user.getUser_aiais());
        return user;
    }
}
