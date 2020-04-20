package com.clim.comment.model.dto;

public class AddComDto {
    private String content;
    private String user_id;
    private String msg_id;
    private String friend_id;

    public AddComDto() {
    }

    public AddComDto(String content, String user_id, String msg_id) {
        this.content = content;
        this.user_id = user_id;
        this.msg_id = msg_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
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
}
