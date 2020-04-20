package com.clim.chat.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class LastMsg {
    private java.sql.Timestamp datetime;
    private String content;

    public LastMsg() {
    }

    @JsonFormat(pattern="MM-dd HH:mm")
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
