package com.clim.blog.dao;


import com.clim.blog.model.entity.*;
import com.clim.blog.model.vo.BlogVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "blogDao")
public interface BlogDao {
   public int insert_img(List<FriendCircleImg> list);
   public int send(@Param(value = "msg_id")String msg_id,
                   @Param(value = "user_id")String user_id,
                   @Param(value = "content")String content);
   public List<String> find_all_friend(@Param(value = "user_id")String user_id);
   public List<String> find_all_msgId(@Param(value = "user_id")String user_id);
   public List<BlogVo> find_blog(List<String> list);
   public List<BlogLike> find_all_like(List<String> msg_id);
   public List<CommentId> find_all_comment(List<String> msg_id);
   public List<ReplyId> find_all_reply(List<String> msg_id);
   public int insert_timeline(@Param(value = "msg_id")String msg_id,
                              @Param(value = "user_id")String user_id,
                              @Param(value = "is_own")int is_own);
   public int insert_friend_timeline(List<CommentId> list);
   public void like(@Param(value = "msg_id")String msg_id,
                    @Param(value = "user_id")String user_id);
   public void cancel_like(@Param(value = "msg_id")String msg_id,
                           @Param(value = "user_id")String user_id);
}
