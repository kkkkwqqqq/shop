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
    public List<Commodity> selectProById(Integer userid) {
        return commodityMapper.selectByuserId(userid);
    }

    @Override
    public Integer updateMyProByid(Commodity commodity) {
        return commodityMapper.updateByPrimaryKeySelective(commodity);
    }

    @Override
    public Integer deleteProByid(Integer commid,Integer userid) {
        return commodityMapper.deleteByPrimaryKey(commid,userid);
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

    @Override
    public void updateLiulanLBycomid(Integer key, Integer liulan) {
       commodityMapper.updateLiulanLBycomid(key,liulan);
    }

    @Override
    public List<Commodity> selectProById1(Integer commid) {
        return commodityMapper.selectByuserId1(commid);
    }

    @Override
    public Commodity selectProByIdAndCid(Integer commid, Integer userid) {
        return commodityMapper.selectProByIdAndCid(commid,userid);
    }

    @Override
    public Integer deleteProByid1(Integer commid) {
        return commodityMapper.deleteByCommid(commid);
    }

    @Override
    public Integer updateMyProByAdmin(Integer commid) {
        return commodityMapper.updateMyProByAdmin(commid);
    }
}
