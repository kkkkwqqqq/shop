package com.fight.mapper;

import com.fight.pojo.Chatmsg;

public interface ChatmsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chatmsg record);

    int insertSelective(Chatmsg record);

    Chatmsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chatmsg record);

    int updateByPrimaryKeyWithBLOBs(Chatmsg record);

    int updateByPrimaryKey(Chatmsg record);
}