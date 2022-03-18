package com.fight.controller;

import com.fight.pojo.Comment;
import com.fight.service.CommentService;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping("/addComment")
    public Object addComment(Comment comment){
        Integer count = commentService.addComment(comment);
        if (count==1){
            return new ResultVo(200,"添加评论成功");
        }else {
            return new ResultVo(100,"评论失败");
        }

    }
    @ResponseBody
    @RequestMapping("/showComment")
    public Object showComment(Integer commid,Integer start,Integer end){
        if (commid==null){
            return new ResultVo(100, "商品id不能为空");
        }

        if (start == null) {
            return new ResultVo(100, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(100, "每页含有多少条商品记录不能为空");
        }
        List<Comment> comments = commentService.showComment(commid,start,end);
        if (comments==null){
            return new ResultVo(100,"显示所有评论失败");
        }else {
            return new ResultVo(200,"显示所有评论成功",comments);
        }


    }
}
