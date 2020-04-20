package com.clim.chat.service;


import com.clim.provider.service.WsPushMsgApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sns-provider-im")
public interface WsPushMsgService extends WsPushMsgApi {
}
