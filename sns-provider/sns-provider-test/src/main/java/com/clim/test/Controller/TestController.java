package com.clim.test.Controller;

import com.clim.common.model.Result;
import com.clim.common.util.ResultUtil;
import com.clim.provider.Service.UserApi;
import com.clim.test.Service.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TestController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserApi userApi;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @GetMapping("/hi")
    public String hi(){
        logger.info("test-hi");
        return userApi.hi();
    }

    @GetMapping("test-util")
    public String testUtil(){
         redisTemplate.opsForValue().set("clim","123");
         return redisTemplate.opsForValue().get("clim");
    }

    @PostMapping("/login")
    public Result<User> getUser(){
        logger.info("test-user1");
        return ResultUtil.success(
                userService.selectUser()
        );
    }
}
