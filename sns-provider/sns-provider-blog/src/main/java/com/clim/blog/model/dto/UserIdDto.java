package com.clim.blog.model.dto;

import java.util.Objects;

public class UserIdDto {
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdDto userIdDto = (UserIdDto) o;
        return user_id.equals(userIdDto.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

    @Override
    public String toString() {
        return "UserIdDto{" +
                "user_id='" + user_id + '\'' +
                '}';
    }
}
