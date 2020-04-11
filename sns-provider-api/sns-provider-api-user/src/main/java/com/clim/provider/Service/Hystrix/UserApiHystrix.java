package com.clim.provider.Service.Hystrix;

import com.clim.provider.Service.UserApi;
import org.springframework.stereotype.Component;

@Component
public class UserApiHystrix implements UserApi {
    @Override
    public String hi() {
        return "sorry";
    }
}
