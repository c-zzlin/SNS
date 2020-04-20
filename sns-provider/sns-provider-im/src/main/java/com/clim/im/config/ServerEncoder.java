package com.clim.im.config;

import com.alibaba.fastjson.JSONArray;
import com.clim.im.model.dto.SendMsg;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ServerEncoder implements Encoder.Text<SendMsg> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public String encode(SendMsg sendMsg) throws EncodeException {
        try {
            String jsonObject = JSONArray.toJSON(sendMsg).toString();
            return jsonObject;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}

