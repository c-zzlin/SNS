package com.clim.provider.service.Hystrix;

import com.clim.provider.service.WsPushApi;
import org.springframework.stereotype.Component;

@Component
public class WsPushApiHystrix implements WsPushApi {



    @Override
    public boolean friendPush(String user_id, String type) {
        return false;
    }
}
