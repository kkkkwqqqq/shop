package com.fight.service.impl;

import com.fight.mapper.CommimagesMapper;
import com.fight.pojo.Commimages;
import com.fight.service.CommimagesService;
import com.fight.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommimagesServiceImpl implements CommimagesService {
    @Autowired
    private CommimagesMapper commimagesMapper;
    @Override
    public List<Commimages> selectImageBy(Integer commid) {
        return commimagesMapper.selectImageBy(commid);
    }

    @Override
    public Integer updateCommimages(Commimages commimages) {
        return commimagesMapper.updateByPrimaryKeySelective(commimages);
    }

    @Override
    public Integer insertAllCommimages(Commimages commimages) {
        return commimagesMapper.insertSelective(commimages);
    }

    @Override
    public Integer deleteImagByid(int id) {
        return commimagesMapper.deleteByPrimaryKey(id);
    }
}
