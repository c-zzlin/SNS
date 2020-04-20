package com.clim.blog.controller;

import com.clim.blog.model.dto.LikeDto;
import com.clim.blog.model.dto.MsgDto;
import com.clim.blog.model.dto.UserIdDto;
import com.clim.blog.model.entity.Blog;
import com.clim.blog.model.entity.BlogLike;
import com.clim.blog.model.entity.FriendCircleImg;
import com.clim.blog.service.BlogService;
import com.clim.blog.util.DateUtil;
import com.clim.common.model.Result;
import com.clim.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/blog")
public class BlogController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/upload")
    public Map<String, String> importDetailDataExcel(@RequestParam("file") MultipartFile[] file)
            throws RuntimeException, IOException {
        Map<String, String> mapResult = new HashMap<String, String>(); // 新建map向客户端返回数据
        List<FriendCircleImg> list=new ArrayList<>();
        String image = DateUtil.getDate();
        String msg_id = blogService.getKey();   //生成ID
        logger.info("====id为： "+msg_id);
        if(file.length>0){
            for(int i=0;i<file.length;i++){
                if (!file[i].isEmpty()) {
                    // 设置文件上传保存的路径
                    String path = "/Users/clim/WebstormProjects/final2/static/upload";
                    String originFileName = file[i].getOriginalFilename(); // 获取文件的原始文件名
                    String prefix = originFileName.substring(originFileName.lastIndexOf('.') + 1);
                    File file2 = new File(path,image+"_"+i + "."+prefix);
                    file[i].transferTo(file2); // 将文件写入磁盘
                    FriendCircleImg friendCircleImg=new FriendCircleImg();
                    friendCircleImg.setImg_order(i);
                    friendCircleImg.setImg_url("/upload/"
                            +image+"_"+i + "."+prefix);
                    friendCircleImg.setMsg_id(msg_id);

                    list.add(i,friendCircleImg);
                }}
            int id = blogService.insert_img(list);
        }
        else{
            logger.info("没有文件:  "+file.length);
        }
        mapResult.put("msg_id",msg_id);
        return mapResult;
    }


    @RequestMapping("/send")
    public Result<Integer> send(@RequestBody MsgDto msgDto){
        return ResultUtil.success(
                blogService.send(msgDto)
        );
    }

    /**
     * 查询所有动态
     */
    @RequestMapping("/search")
    public Result<List<Blog>> search(@RequestBody UserIdDto userIdDto){
        return ResultUtil.success(
                blogService.search_blog(userIdDto.getUser_id())
        );
    }

    @RequestMapping("/like")
    public Result like(@RequestBody LikeDto likeDto){
            blogService.like(likeDto);
            return ResultUtil.success();
    }


    @RequestMapping("/test")
    public Result<List<BlogLike>> test(@RequestBody UserIdDto userIdDto){
        return ResultUtil.success(
                blogService.test(userIdDto.getUser_id())
        );
    }
}
