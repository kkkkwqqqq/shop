package com.fight.service.impl;

import com.fight.mapper.CommentMapper;
import com.fight.pojo.Comment;
import com.fight.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Integer addComment(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> showComment(Integer commid, Integer start, Integer end) {
       return commentMapper.selectByCommid(commid, start, end);
    }
}
