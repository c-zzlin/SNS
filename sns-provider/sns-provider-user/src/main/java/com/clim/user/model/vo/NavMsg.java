package com.clim.user.model.vo;

import java.io.Serializable;

public class NavMsg implements Serializable {
    private int like;
    private int msg;
    private int friend;

    public NavMsg() {
    }

    public NavMsg(int like, int msg, int friend) {
        this.like = like;
        this.msg = msg;
        this.friend = friend;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }
}
