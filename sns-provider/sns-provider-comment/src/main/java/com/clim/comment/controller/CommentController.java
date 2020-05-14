package com.clim.comment.controller;


import com.clim.comment.model.dto.AddComDto;
import com.clim.comment.model.dto.CommentIdDto;
import com.clim.comment.model.dto.ReplyDto;
import com.clim.comment.model.vo.CommentVo;
import com.clim.comment.service.CommentService;
import com.clim.common.model.Result;
import com.clim.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    CommentService commentService;
    @RequestMapping("/search")
    public Result<List<CommentVo>> search(@RequestBody CommentIdDto commentIdDto){
        logger.info("查询评论，commentIdDto={}",commentIdDto);
        return ResultUtil.success(
                commentService.list(commentIdDto)
        );
    }

    @RequestMapping("/insert")
    public Result<BigInteger> insert(@RequestBody AddComDto addComDto){
        logger.info("添加评论, addComDto={}",addComDto);
            return ResultUtil.success(
                    commentService.insert(addComDto)
            );

    }

    @RequestMapping("/reply")
    public Result<BigInteger> reply(@RequestBody ReplyDto replyDto){
        logger.info("添加回复， replyDto={}",replyDto);
        return ResultUtil.success(
                commentService.reply(replyDto)
        );
    }

}
