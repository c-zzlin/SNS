package com.clim.user.model.vo;

public class UserLoginVo {
    private String user_id;
    private String user_aiais;
    private String user_image;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public UserLoginVo(String user_id, String user_aiais, String user_image, String token) {
        this.user_id = user_id;
        this.user_aiais = user_aiais;
        this.user_image = user_image;
        this.token = token;
    }
}
