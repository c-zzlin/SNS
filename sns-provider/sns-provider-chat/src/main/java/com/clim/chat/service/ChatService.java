package com.clim.chat.service;

import com.clim.chat.model.vo.FriendListVo;
import com.clim.chat.model.vo.RecordVo;
import com.clim.provider.model.SendMsgDto;

import java.util.List;

public interface ChatService {
    public List<String> query_friendId(String user_id);
    public List<FriendListVo> friendList(String user_id);
    public List<RecordVo> record(String user_id, String reply_id);
    public int send(SendMsgDto sendMsgDto);
    public int urcount(String user_id);
}
