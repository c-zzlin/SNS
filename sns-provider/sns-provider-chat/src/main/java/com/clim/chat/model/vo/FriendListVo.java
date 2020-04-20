package com.clim.chat.model.vo;

import com.clim.chat.model.dto.LastMsg;
import com.clim.chat.model.dto.User;

public class FriendListVo {
    private User user;
    private LastMsg lastMsg;
    private int sum;

    public FriendListVo(User user, LastMsg lastMsg, int sum) {
        this.user = user;
        this.lastMsg = lastMsg;
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LastMsg getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(LastMsg lastMsg) {
        this.lastMsg = lastMsg;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
