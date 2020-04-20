package com.clim.provider.service.Hystrix;

import com.clim.provider.model.SendMsgDto;
import com.clim.provider.service.WsPushMsgApi;
import org.springframework.stereotype.Component;

@Component
public class WsPushMsgApiHystrix implements WsPushMsgApi {
    @Override
    public boolean friendPush(SendMsgDto sendMsgDto) {
        return false;
    }
}
