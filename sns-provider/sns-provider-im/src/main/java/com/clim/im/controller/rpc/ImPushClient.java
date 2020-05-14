package com.clim.im.controller.rpc;

import com.clim.im.controller.WebSocketServer;
import com.clim.provider.service.WsPushApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
public class ImPushClient implements WsPushApi {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    WebSocketServer webSocketServer;



    @Override
    public boolean friendPush(String user_id, String type) {
        try {
            webSocketServer.sendTips(type, user_id);
            return true;
        } catch (IOException e) {
            logger.error("push失败： user_id={}, type={}",user_id,type);
            e.printStackTrace();
        } catch (EncodeException e) {
            logger.error("push失败： user_id={}, type={}",user_id,type);
            e.printStackTrace();
        }
        return false;
    }
}
