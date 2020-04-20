package com.clim.friend.controller.rpc;

import com.clim.common.model.Result;
import com.clim.common.util.ResultUtil;
import com.clim.friend.model.dto.UserAddDto;
import com.clim.friend.model.dto.UserAiaisDto;
import com.clim.friend.model.dto.UserIdDto;
import com.clim.friend.model.entity.User;
import com.clim.friend.model.vo.FriendListVo;
import com.clim.friend.service.FriendService;
import com.oracle.tools.packager.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    Logger logger = LoggerFactory.getLogger(getClass());
    @PostMapping("/list")
    public Result<List<FriendListVo>> getFriendList(@RequestBody UserIdDto userIdDto){

        List<FriendListVo> list = friendService.getFriendList(userIdDto.getUser_id());
        return ResultUtil.success(list);
    }

    @PostMapping("/find")
    public Result<List<FriendListVo>> find(@RequestBody UserAiaisDto user_aiais){
        logger.info("find======参数为："+user_aiais);
        List<FriendListVo> list = friendService.find(
                user_aiais.getUser_aiais(),
                user_aiais.getUser_id());
        return ResultUtil.success(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody UserAddDto userAddDto){
                int res = friendService.add(
                        userAddDto.getUser_id(),
                        userAddDto.getFriend_id()
                );
                return ResultUtil.success();
    }

    /**
     * 查看好友申请列表
     * @param userIdDto
     * @return
     */
    @PostMapping("/commit")
    public Result<List<FriendListVo>> commit(@RequestBody UserIdDto userIdDto){
        List<FriendListVo> list = friendService.commit(userIdDto.getUser_id());
        return ResultUtil.success(list);
    }

    @PostMapping("/unread")
    public Result<Integer> unread(@RequestBody UserIdDto userIdDto){
            return ResultUtil.success(
                    friendService.unread(userIdDto.getUser_id())
            );
    }

    @PostMapping("/confirm")
    public Result<Integer> confirm(@RequestBody UserAddDto userAddDto){
        return ResultUtil.success(
                friendService.confirm(
                        userAddDto.getUser_id(),
                        userAddDto.getFriend_id()
                        )
        );

    }

    @PostMapping("/delete")
    public Result delete(@RequestBody UserAddDto userAddDto){
            int res = friendService.delete(
                    userAddDto.getUser_id(),
                    userAddDto.getFriend_id());
            logger.info("res===== "+res);
            return ResultUtil.success();
    }
}
