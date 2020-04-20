package com.clim.friend.model.vo;

public class FriendListVo {
    private String user_id;
    private String user_aiais;
    private String user_image;
    private String user_info;
    private String user_sex;


    public FriendListVo(String user_id, String user_aiais, String user_image, String user_info, String user_sex) {
        this.user_id = user_id;
        this.user_aiais = user_aiais;
        this.user_image = user_image;
        this.user_info = user_info;
        this.user_sex = user_sex;
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

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }
}
