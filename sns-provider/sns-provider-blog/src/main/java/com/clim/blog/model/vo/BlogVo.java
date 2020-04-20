package com.clim.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class BlogVo {
    private String msg_id;
    private String user_id;
    private String user_image;
    private String user_aiais;
    private String msg_content;
    private java.sql.Timestamp create_time;
    private List<ImgGroup> img_group;
    private int is_like;
    private int like;
    private int comment;

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

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    @JsonFormat(pattern="MM-dd HH:mm")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public List<ImgGroup> getImg_group() {
        return img_group;
    }

    public void setImg_group(List<ImgGroup> img_group) {
        this.img_group = img_group;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
