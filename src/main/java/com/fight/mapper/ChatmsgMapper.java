package com.fight.mapper;

import com.fight.pojo.Chatmsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatmsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chatmsg record);

    int insertSelective(Chatmsg record);

    Chatmsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chatmsg record);

    int updateByPrimaryKeyWithBLOBs(Chatmsg record);

    int updateByPrimaryKey(Chatmsg record);


   List<Chatmsg>  selectCharByUserid(Integer senduserid, Integer reciveuserid);
}
