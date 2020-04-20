package com.clim.friend.service;

import com.clim.friend.model.vo.FriendListVo;

import java.util.List;

public interface FriendService {
    public List<FriendListVo> getFriendList(String user_id);
    public List<FriendListVo> find(String user_aiais, String user_id);
    public int add(String user_id,String friend_id);
    public List<FriendListVo> commit(String user_id);
    public int unread(String uesr_id);
    public int confirm(String user_id, String friend_id);
    public int delete(String user_id, String friend_id);
}
