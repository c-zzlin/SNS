package com.clim.blog.model.entity;

import com.clim.blog.model.vo.ImgGroup;

import java.sql.Timestamp;
import java.util.List;

public class Blog {
    private String msg_id;
    private String user_id;
    private String user_image;
    private String user_aiais;
    private String msg_content;
    private java.sql.Timestamp create_time;
    private List<ImgGroup> img_group;

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
}
