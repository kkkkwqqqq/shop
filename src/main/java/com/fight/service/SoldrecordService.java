package com.fight.service;


import com.fight.pojo.soldrecord;

import java.util.List;

public interface SoldrecordService {
    //生成一条售出记录
    public Integer addSoldRecord(soldrecord soldrecord);

   public Integer updateStatus(soldrecord soldrecord);

    List<soldrecord> selectSoldrecordByuserid(Integer userid,int page,int count);
}
