package com.clim.comment.service;

import com.clim.comment.model.dto.AddComDto;
import com.clim.comment.model.dto.CommentIdDto;
import com.clim.comment.model.dto.ReplyDto;
import com.clim.comment.model.vo.CommentVo;

import java.math.BigInteger;
import java.util.List;


public interface CommentService {
    public List<CommentVo> list(CommentIdDto commentIdDto);
    public BigInteger insert(AddComDto addComDto);
    public BigInteger reply(ReplyDto replyDto);
}
