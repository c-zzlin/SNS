package com.clim.im.controller.rpc;

import com.clim.im.controller.WebSocketServer;
import com.clim.provider.service.WsPushApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
public class ImPushClient implements WsPushApi {
    @Autowired
    WebSocketServer webSocketServer;



    @Override
    public boolean friendPush(String user_id, String type) {
        try {
            webSocketServer.sendTips(type, user_id);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        return false;
    }
}
