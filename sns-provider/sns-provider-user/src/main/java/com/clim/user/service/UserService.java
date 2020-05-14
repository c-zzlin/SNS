package com.clim.user.service;


import com.clim.user.model.entity.User;
import com.clim.user.model.vo.NavMsg;
import com.clim.user.model.vo.UserLoginVo;

public interface UserService {
    public UserLoginVo login(String user_logon, String user_password);
    public boolean logout(String token);
    public NavMsg query_navMsg(String user_id);
}
