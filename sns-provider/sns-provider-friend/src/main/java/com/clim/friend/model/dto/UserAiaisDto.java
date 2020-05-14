package com.clim.friend.model.dto;

public class UserAiaisDto {
    private String user_aiais;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_aiais() {
        return user_aiais;
    }

    public void setUser_aiais(String user_aiais) {
        this.user_aiais = user_aiais;
    }

    @Override
    public String toString() {
        return "UserAiaisDto{" +
                "user_aiais='" + user_aiais + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
