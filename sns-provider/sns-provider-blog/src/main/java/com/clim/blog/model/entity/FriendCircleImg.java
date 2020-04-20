package com.clim.blog.model.entity;

public class FriendCircleImg {
    private String msg_id;
    private String img_url;
    private int img_order;

    public FriendCircleImg() {
    }

    public FriendCircleImg(String msg_id, String img_url, int img_order) {
        this.msg_id = msg_id;
        this.img_url = img_url;
        this.img_order = img_order;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getImg_order() {
        return img_order;
    }

    public void setImg_order(int img_order) {
        this.img_order = img_order;
    }
}
