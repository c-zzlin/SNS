package com.clim.chat.controller;

import com.clim.chat.model.dto.RecordDto;
import com.clim.chat.model.dto.User;
import com.clim.chat.model.dto.UserIdDto;
import com.clim.chat.model.vo.FriendListVo;
import com.clim.chat.model.vo.RecordVo;
import com.clim.chat.service.ChatService;
import com.clim.common.model.Result;
import com.clim.common.util.ResultUtil;
import com.clim.provider.model.SendMsgDto;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @RequestMapping("/friend")
    public Result<List<FriendListVo>> friend(@RequestBody UserIdDto userIdDto){
        return ResultUtil.success(
                chatService.friendList(userIdDto.getUser_id())
        );
    }

    @RequestMapping("/record")
    public Result<List<RecordVo>> record(@RequestBody RecordDto recordDto){
        return ResultUtil.success(
                chatService.record(
                        recordDto.getUser_id(),
                        recordDto.getReply_id()
                )
        );
    }

    @PostMapping("/send")
    public Result send(@RequestBody SendMsgDto sendMsgDto){
        chatService.send(sendMsgDto);
        return ResultUtil.success();
    }

    @PostMapping("/urcount")
    public Result<Integer> urcount(@RequestBody UserIdDto userIdDto){
            try{
                int res = chatService.urcount(userIdDto.getUser_id());
                return ResultUtil.success(res);
            }catch (Exception e){
                return ResultUtil.error(500, e.getMessage());
            }
    }

}
