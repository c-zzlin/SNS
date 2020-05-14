package com.clim.chat.model.dto;

public class RecordDto {
    private String user_id;
    private String reply_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    @Override
    public String toString() {
        return "RecordDto{" +
                "user_id='" + user_id + '\'' +
                ", reply_id='" + reply_id + '\'' +
                '}';
    }
}
