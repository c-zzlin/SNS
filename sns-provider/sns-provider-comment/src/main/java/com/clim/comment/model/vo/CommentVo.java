package com.clim.comment.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class CommentVo {
    private String user_image;
    private String user_aiais;
    private String user_id;
    private String comment_id;
    private String content;
    private java.sql.Timestamp create_time;
    private List<ReplyVo> list;

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_aiais() {
        return user_aiais;
    }

    public void setUser_aiais(String user_aiais) {
        this.user_aiais = user_aiais;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @JsonFormat(pattern="MM-dd HH:mm")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public List<ReplyVo> getList() {
        return list;
    }

    public void setList(List<ReplyVo> list) {
        this.list = list;
    }
}
