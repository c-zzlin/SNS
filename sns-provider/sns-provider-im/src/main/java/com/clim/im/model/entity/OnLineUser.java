package com.clim.im.model.entity;

import javax.websocket.Session;

public class OnLineUser {

    private Session session;
    private String from;
    public OnLineUser() {}

    public OnLineUser(Session session, String from) {
        this.session = session;
        this.from = from;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
