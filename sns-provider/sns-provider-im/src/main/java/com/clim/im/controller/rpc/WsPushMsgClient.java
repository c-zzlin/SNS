package com.clim.im.controller.rpc;


import com.clim.im.controller.WebSocketServer;
import com.clim.im.model.dto.SendMsg;
import com.clim.provider.model.SendMsgDto;
import com.clim.provider.service.WsPushMsgApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
public class WsPushMsgClient implements WsPushMsgApi {
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
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        return false;
    }
}
