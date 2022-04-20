package com.fight.mapper;

import com.fight.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByCommid(Integer commid);


}
