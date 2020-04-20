package com.clim.like;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.clim.friend.dao")
public class LikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LikeApplication.class, args);
    }


}
