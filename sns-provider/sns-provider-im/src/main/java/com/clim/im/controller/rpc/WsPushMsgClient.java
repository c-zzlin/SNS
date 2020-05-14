package com.clim.im.controller.rpc;


import com.clim.im.controller.WebSocketServer;
import com.clim.im.model.dto.SendMsg;
import com.clim.provider.model.SendMsgDto;
import com.clim.provider.service.WsPushMsgApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
public class WsPushMsgClient implements WsPushMsgApi {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    WebSocketServer webSocketServer;
    @Override
    public boolean friendPush(SendMsgDto sendMsgDto) {
        try {
            webSocketServer.sendOnlineFriend(new SendMsg(
                    sendMsgDto.getContent(),
                    sendMsgDto.getTo(),
                    sendMsgDto.getFrom()));
            return true;
        } catch (IOException e) {
            logger.info("push失败， sendMsgDto={}", sendMsgDto);
            e.printStackTrace();
        } catch (EncodeException e) {
            logger.info("push失败， sendMsgDto={}", sendMsgDto);
            e.printStackTrace();
        }
        return false;
    }
}
