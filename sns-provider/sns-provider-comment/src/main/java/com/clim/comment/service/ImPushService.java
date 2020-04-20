package com.clim.comment.service;


import com.clim.provider.service.WsPushApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sns-provider-im")
public interface ImPushService extends WsPushApi {
}
