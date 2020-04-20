package com.clim.blog.model.entity;

public class CommentId {
    private String msg_id;
    private String user_id;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public CommentId() {
    }

    public CommentId(String msg_id, String user_id) {
        this.msg_id = msg_id;
        this.user_id = user_id;
    }
}
