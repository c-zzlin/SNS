package com.clim.provider.service;

import com.clim.provider.model.SendMsgDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface WsPushMsgApi {
    @PostMapping("/ws/push/msg")
    public boolean friendPush(@RequestBody SendMsgDto sendMsgDto);
}
