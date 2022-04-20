package com.fight.mapper;

import com.fight.pojo.soldrecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface soldrecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(soldrecord record);

    int insertSelective(soldrecord record);

    soldrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(soldrecord record);

    int updateByPrimaryKey(soldrecord record);

    List<soldrecord> selectByPrimaryKey1(Integer userid,int page,int count);

    Integer updateIsPay(int i,String orderid);

    void deleteByOrderid(String orderid);

    Integer updateStatus1(String status, String orderid);

    List<soldrecord> allSoldRecord(int userid);
}
