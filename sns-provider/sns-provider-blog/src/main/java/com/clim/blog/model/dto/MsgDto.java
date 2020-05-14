package com.clim.blog.model.dto;

import java.io.Serializable;

public class MsgDto implements Serializable {
    private String msg_id;
    private String content;
    private String user_id;

    @Override
    public String toString() {
        return "MsgDto{" +
                "msg_id='" + msg_id + '\'' +
                ", content='" + content + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public MsgDto() {
    }

    public MsgDto(String msg_id, String content, String user_id) {
        this.msg_id = msg_id;
        this.content = content;
        this.user_id = user_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
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
}
