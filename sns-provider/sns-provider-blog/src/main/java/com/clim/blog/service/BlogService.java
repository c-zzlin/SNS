package com.clim.blog.service;

import com.clim.blog.model.dto.LikeDto;
import com.clim.blog.model.dto.MsgDto;
import com.clim.blog.model.entity.Blog;
import com.clim.blog.model.entity.BlogLike;
import com.clim.blog.model.entity.FriendCircleImg;
import com.clim.blog.model.vo.BlogVo;

import java.util.List;


public interface BlogService {
    public int insert_img(List<FriendCircleImg> list);
    public String getKey();
    public int send(MsgDto msgDto);
    public void insert_timeline(MsgDto msgDto);
    public List<BlogVo> search_blog(String user_id);
    public void like(LikeDto likeDto);
}
