package com.fight.service.impl;

import com.fight.mapper.soldrecordMapper;
import com.fight.pojo.soldrecord;
import com.fight.service.SoldrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldrecordServiceImpl implements SoldrecordService {
    @Autowired
    private soldrecordMapper soldrecordMapper;

    @Override
    public Integer addSoldRecord(soldrecord soldrecord) {
        return soldrecordMapper.insertSelective(soldrecord);
    }

    @Override
    public Integer updateStatus(soldrecord soldrecord) {
        return soldrecordMapper.updateByPrimaryKeySelective(soldrecord);
    }

    @Override
    public List<soldrecord> selectSoldrecordByuserid(Integer userid,int page,int count) {
       return   soldrecordMapper.selectByPrimaryKey1(userid,page,count);}

    @Override
    public Integer updateIsPay(int i,String orderid) {
        return soldrecordMapper.updateIsPay(1,orderid);
    }

    @Override
    public void deleteRecordById(String orderid) {
       soldrecordMapper.deleteByOrderid(orderid);
    }

    @Override
    public Integer updateStatus1(String status, String orderid) {
        return soldrecordMapper.updateStatus1(status,orderid);
    }

    @Override
    public List<soldrecord> allSoldRecord(int sellerid) {
        return soldrecordMapper.allSoldRecord(sellerid);
    }
}
