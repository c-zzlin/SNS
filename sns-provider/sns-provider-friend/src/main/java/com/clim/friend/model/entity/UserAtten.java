package com.clim.friend.model.entity;

public class UserAtten {
    private String user_id;
    private String atten_id;
    private int status;
    private int offline;

    public UserAtten(String user_id, String atten_id, int status, int offline) {
        this.user_id = user_id;
        this.atten_id = atten_id;
        this.status = status;
        this.offline = offline;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAtten_id() {
        return atten_id;
    }

    public void setAtten_id(String atten_id) {
        this.atten_id = atten_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }
}
