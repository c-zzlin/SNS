package com.clim.blog.util.consumer;


import com.clim.blog.model.dto.MsgDto;
import com.clim.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeLineConsumer {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    BlogService blogService;

    @RabbitListener(queues = "insert_timeline_send")
    public void consume(MsgDto msgDto){
        logger.info("======consume====参数：  "+msgDto.toString());
        blogService.insert_timeline(msgDto);
    }
}
