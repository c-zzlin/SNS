package com.clim.comment.model.entity;

import java.math.BigInteger;

public class ReplyBean {
    private String reply_id;
    private String msg_id;
    private String user_id;
    private String content;
    private BigInteger comment_id;

    public ReplyBean() {
    }


    public ReplyBean(String reply_id, String msg_id, String user_id, String content, BigInteger comment_id) {
        this.reply_id = reply_id;
        this.msg_id = msg_id;
        this.user_id = user_id;
        this.content = content;
        this.comment_id = comment_id;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigInteger getComment_id() {
        return comment_id;
    }

    public void setComment_id(BigInteger comment_id) {
        this.comment_id = comment_id;
    }
}
