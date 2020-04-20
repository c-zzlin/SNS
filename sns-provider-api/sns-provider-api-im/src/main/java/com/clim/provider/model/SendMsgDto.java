package com.clim.provider.model;

public class SendMsgDto {
    public String content;
    public String to;
    public String from;

    @Override
    public String toString() {
        return "SendMsgDto{" +
                "content='" + content + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                '}';
    }

    public SendMsgDto(String content, String to, String from) {
        this.content = content;
        this.to = to;
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
