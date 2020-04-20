package com.clim.chat.dao;

import com.clim.chat.model.dto.LastMsg;
import com.clim.chat.model.dto.User;
import com.clim.chat.model.vo.RecordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "chatDao")
public interface ChatDao {
    public List<String> query_friendId(@Param(value = "user_id")String user_id);
    public List<User> query_friend_user(@Param(value = "list")List<String> list);
    public LastMsg query_friend_lastmsg(@Param(value = "user_id")String user_id,
                                        @Param(value = "reply_id")String reply_id);
    public int un_read_count(@Param(value = "user_id")String user_id,
                             @Param(value = "reply_id")String reply_id);
    public List<RecordVo> record(@Param(value = "user_id")String user_id,
                                @Param(value = "reply_id")String reply_id);
    public int unread(@Param(value = "user_id")String user_id,
                      @Param(value = "reply_id")String reply_id);
    public int send(@Param(value = "content")String content,
                    @Param(value = "to")String to,
                    @Param(value = "from")String from);
    public int urcount(@Param(value = "user_id")String user_id);
}
