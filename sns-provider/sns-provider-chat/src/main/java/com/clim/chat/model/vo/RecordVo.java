package com.clim.chat.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class RecordVo {
    private String user_id;
    private String content;
    private java.sql.Timestamp datetime;

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
    @JsonFormat(pattern="MM-dd HH:mm")
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}
