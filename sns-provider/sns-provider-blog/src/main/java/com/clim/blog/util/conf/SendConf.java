package com.clim.blog.util.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendConf {

    public static String TIMELINE_QUEUE = "insert_timeline_send";
    @Bean
    public Queue queue(){
        return new Queue(TIMELINE_QUEUE);
    }
}
