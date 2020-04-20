package com.clim.im;

import com.clim.im.controller.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.clim.im.dao")
public class ImApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);

    }


}
