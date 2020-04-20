package com.clim.friend.dao;

import com.clim.friend.model.dto.TimeLineDto;
import com.clim.friend.model.entity.User;
import com.clim.friend.model.vo.FriendListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "firendDao")
public interface FriendDao {
    public List<FriendListVo> getFriendList(@Param(value = "user_id")String user_id);
    public List<FriendListVo> find(@Param(value = "user_aiais")String user_aiais,
                                   @Param(value = "user_id")String user_id);
    public int add(@Param(value = "user_id")String user_id,
                   @Param(value = "friend_id")String friend_id,
                   @Param(value = "status")int status,
                   @Param(value = "offline")int offline);
    public List<FriendListVo> commit(@Param(value = "user_id")String user_id);
    public int unread(@Param(value = "user_id")String user_id);
    public int confirm(@Param(value = "user_id")String user_id,
                       @Param(value = "friend_id")String friend_id);
    public int delete(@Param(value = "user_id")String user_id,
                      @Param(value = "friend_id")String friend_id);
    public List<String> friend_timeline_list(@Param(value = "user_id")String user_id);
    public int insert_friend_timeline(List<TimeLineDto> list);
    public int delete_All_timeline(@Param(value = "list") List<String> list,
                                   @Param(value = "user_id")String user_id);
}
