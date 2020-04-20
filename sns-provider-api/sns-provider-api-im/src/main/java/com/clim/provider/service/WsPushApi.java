package com.clim.provider.service;


import com.clim.provider.service.Hystrix.WsPushApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "sns-provider-user",fallback = WsPushApiHystrix.class)
public interface WsPushApi {

    @PostMapping("/ws/push/{user_id}/{type}")
    public boolean friendPush(@PathVariable("user_id") String user_id,
                              @PathVariable("type")String type);
}
