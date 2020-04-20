package com.clim.user.controller.rpc;

import com.clim.provider.Service.UserApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClient implements UserApi {
    @Override
    public String hi() {
        System.out.println("123");
        return "client-user1";
    }
}
