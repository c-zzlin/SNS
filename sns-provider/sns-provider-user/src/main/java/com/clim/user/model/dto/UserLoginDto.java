package com.clim.user.model.dto;

public class UserLoginDto {
    private String user_logon;
    private String user_password;

    public String getUser_logon() {
        return user_logon;
    }

    public void setUser_logon(String user_logon) {
        this.user_logon = user_logon;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
