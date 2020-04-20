package com.clim.blog.model.entity;

import java.util.List;

public class BlogLike {
    private String msg_id;
    private List<String> user_id;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public List<String> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<String> user_id) {
        this.user_id = user_id;
    }
}
