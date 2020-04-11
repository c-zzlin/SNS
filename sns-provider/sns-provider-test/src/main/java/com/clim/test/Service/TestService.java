package com.clim.test.Service;

import com.clim.provider.Service.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sns-provider-user")
public interface TestService extends UserApi {
}
