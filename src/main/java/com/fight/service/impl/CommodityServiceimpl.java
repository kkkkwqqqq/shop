package com.fight.service.impl;

import com.fight.mapper.CommodityMapper;
import com.fight.pojo.Commimages;
import com.fight.pojo.Commodity;
import com.fight.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceimpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> selectNew() {
        return commodityMapper.selectNew();
    }

    @Override
    public List<Commodity> selectByType(String type,Integer start,Integer end) {
        return commodityMapper.selectByType(type,start,end);
    }

    @Override
    public List<Commodity> selectByTypeAndPriceDesc(String type,Integer start,Integer end) {
        return commodityMapper.selectByTypeAndPriceDesc(type,start,end);
    }

    @Override
    public List<Commodity> selectByTypeAndPriceAsc(String type,Integer start,Integer end) {
        return commodityMapper.selectByTypeAndPriceAsc(type,start,end);
    }

    @Override
    public List<Commodity> selectProById(Integer commid,Integer page,Integer count) {
        return commodityMapper.selectByuserId(commid,page,count);
    }

    @Override
    public Integer updateMyProByid(Commodity commodity) {
        return commodityMapper.updateByPrimaryKeySelective(commodity);
    }

    @Override
    public Integer deleteProByid(Integer commid) {
        return commodityMapper.deleteByPrimaryKey(commid);
    }

    @Override
    public List<Commodity> selectweishen(Commodity commodity) {
      return   commodityMapper.selectweishen(commodity);
    }

    @Override
    public List<Commodity> selectAllPro() {
        return commodityMapper.selectAllPro();
    }

    @Override
    public List<Commodity> selectweishenPro(Integer status) {
        return commodityMapper.selectweiPro(status);
    }

    @Override
    public Integer publishCommodity(Commodity commodity) {
        return commodityMapper.insertSelective(commodity);
    }

    @Override
    public Commodity selectProBynameAnduserId(String commname, Integer userid) {
        return commodityMapper.selectProBynameAnduserId(commname,userid);
    }

    @Override
    public List<Commodity> select() {
        return commodityMapper.selectNew();
    }
}
