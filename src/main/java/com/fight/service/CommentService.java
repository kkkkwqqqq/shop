package com.fight.service;

import com.fight.pojo.Comment;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface CommentService {
    /*添加评论*/
    public Integer addComment(Comment comment);



    List<Comment> showComment(Integer commid);


    Integer deleteCommentByCid(Integer cid);
}
