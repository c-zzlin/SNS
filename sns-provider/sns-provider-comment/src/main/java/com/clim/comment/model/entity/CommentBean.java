package com.clim.comment.model.entity;

import java.math.BigInteger;

public class CommentBean {
    private String content;
    private String user_id;
    private String msg_id;
    private BigInteger comment_id;

    public CommentBean() {
    }


    public CommentBean(String content, String user_id, String msg_id) {
        this.content = content;
        this.user_id = user_id;
        this.msg_id = msg_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public BigInteger getComment_id() {
        return comment_id;
    }

    public void setComment_id(BigInteger comment_id) {
        this.comment_id = comment_id;
    }
}
