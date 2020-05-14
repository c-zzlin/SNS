package com.clim.user.controller;


import com.clim.common.model.Result;
import com.clim.common.util.ResultUtil;
import com.clim.user.model.dto.TokenDto;
import com.clim.user.model.dto.UserIdDto;
import com.clim.user.model.dto.UserLoginDto;
import com.clim.user.model.enums.LoginCode;
import com.clim.user.model.vo.NavMsg;
import com.clim.user.model.vo.UserLoginVo;
import com.clim.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<UserLoginVo> login(@RequestBody UserLoginDto userLoginDto){
        logger.info("登陆请求， userLoginDto={}",userLoginDto);
            UserLoginVo userLoginVo=userService.login(
                    userLoginDto.getUser_logon(),
                    userLoginDto.getUser_password());
            if(userLoginVo == null)
                return ResultUtil.error(LoginCode.FAIL,"登陆失败，账号或密码不正确");

            return ResultUtil.success(userLoginVo);
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody TokenDto tokenDto){
        logger.info("退出登陆， token={}", tokenDto);
        if(userService.logout(tokenDto.getToken())){
            return ResultUtil.success();
        }
        return ResultUtil.error(LoginCode.FAIL, "未知异常");
    }


    /**
     * 查找点赞数，好友数，动态数
     * @param userIdDto
     * @return
     */
    @PostMapping("/navMsg")
    public Result<NavMsg> navMsg(@RequestBody UserIdDto userIdDto){
        logger.info("查找点赞数、好友数.  userIdDto={}",userIdDto);
            return ResultUtil.success(
                    userService.query_navMsg(userIdDto.getUser_id())
            );
    }


    @GetMapping("/hi")
    public String hi() {
        logger.info("user-hi");
        return "success";
    }
}
