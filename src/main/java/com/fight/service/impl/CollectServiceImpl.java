package com.fight.service.impl;

import com.fight.mapper.CollectMapper;
import com.fight.pojo.Collect;
import com.fight.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;
    @Override
    public Integer collectPro(Collect collect) {
        return collectMapper.insertSelective(collect);
    }

    @Override
    public Integer giveColPro(Integer commid,Integer couserid) {
      return   collectMapper.giveColPro(commid,couserid);
    }

    @Override
    public Collect existOrNot(Integer commid, Integer couserid) {
        return collectMapper.existOrNot(commid, couserid);
    }

    @Override
    public List<Collect> selectAllCollect(int userid) {
        return collectMapper.selectAllCollect(userid);
    }
}
