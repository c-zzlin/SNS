package com.clim.user.model.dto;

public class UserIdDto {
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserIdDto{" +
                "user_id='" + user_id + '\'' +
                '}';
    }
}
