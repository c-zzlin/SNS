package com.clim.comment.dao;


import com.clim.comment.model.entity.CommentBean;
import com.clim.comment.model.entity.ReplyBean;
import com.clim.comment.model.vo.CommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "commentDao")
public interface CommentDao {
   public List<CommentVo> list(@Param(value = "msg_id")String msg_id);
   public List<String> query_friend_list(@Param(value = "user_id")String user_id);
   public int insert(CommentBean commentBean);
   public int reply(ReplyBean replyBean);
}
