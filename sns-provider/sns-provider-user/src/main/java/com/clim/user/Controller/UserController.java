package com.clim.user.Controller;

import com.clim.provider.Service.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/hi")
    public String hi() {
        logger.info("user-hi");
        return "success";
    }
}
