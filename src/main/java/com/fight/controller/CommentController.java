package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.exception.ShopException;
import com.fight.pojo.Comment;
import com.fight.pojo.Commodity;
import com.fight.service.CommentService;
import com.fight.util.JwtUtil;
import com.fight.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping("/addComment")
    public Object addComment(HttpServletRequest request, Comment comment){
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
       if (Integer.parseInt(userId)==comment.getSpuserid()){
           return new ResultVo(100,"不能评价自己的商品哦");
       }
        Integer count = commentService.addComment(comment);
        if (count==1){
            return new ResultVo(200,"添加评论成功");
        }else {
            return new ResultVo(Constants.FAILED_STATUS,"评论失败");
        }

    }
    @ResponseBody
    @RequestMapping("/showComment")
    public Object showComment(Integer commid,Integer page,Integer count) {
        if (commid == null) {
            return new ResultVo(Constants.FAILED_STATUS, "商品id不能为空");
        }

        if (page == null) {
            return new ResultVo(Constants.FAILED_STATUS, "页数不能为空");
        }
        if (count == null) {
            return new ResultVo(Constants.FAILED_STATUS, "每页含有多少条商品记录不能为空");
        }
        PageHelper.startPage(page, count);
        List<Comment> comments = commentService.showComment(commid);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(comments);
        if (comments == null) {
            return new ResultVo(Constants.FAILED_STATUS, "显示所有评论失败");
        } else {
            return new ResultVo(Constants.SUCCESS_STATUS, "显示评论成功", comments);
        }
    }
        @ResponseBody
        @RequestMapping("/deleteComment")
        public Object showComment(Integer cid){
            Integer count = commentService.deleteCommentByCid(cid);
            if (count == 1) {
                return new ResultVo(Constants.SUCCESS_STATUS, "删除评论成功");
            } else {
                return new ResultVo(Constants.FAILED_STATUS, "删除评论失败");
            }
    }
}
