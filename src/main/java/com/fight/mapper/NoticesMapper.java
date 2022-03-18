package com.fight.mapper;

import com.fight.pojo.Notices;

public interface NoticesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notices record);

    int insertSelective(Notices record);

    Notices selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notices record);

    int updateByPrimaryKey(Notices record);
}