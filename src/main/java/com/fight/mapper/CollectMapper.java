package com.fight.mapper;

import com.fight.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);


    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    Integer giveColPro(Integer commid,Integer couserid);

    Collect existOrNot(Integer commind,Integer couserid);

    List<Collect>  selectAllCollect(@Param("userid") int userid);

}
