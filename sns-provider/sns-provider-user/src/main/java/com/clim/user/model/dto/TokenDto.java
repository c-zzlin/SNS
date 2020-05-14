package com.clim.user.model.dto;

public class TokenDto {
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
