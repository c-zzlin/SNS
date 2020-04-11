package com.clim.provider.Service;

import com.clim.provider.Service.Hystrix.UserApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "sns-provider-user",fallback = UserApiHystrix.class)
public interface UserApi {
    @GetMapping("/hi2")
    public String hi();
}
