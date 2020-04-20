package com.clim.comment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.clim.comment.dao")
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class, args);
    }


}
