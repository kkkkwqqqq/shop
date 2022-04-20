package com.fight.service;

import com.fight.pojo.Collect;

import java.util.List;

public interface CollectService {
    /*收藏商品*/
    public Integer collectPro(Collect collect);

    Integer giveColPro(Integer commid,Integer couserid);

    Collect existOrNot(Integer commid, Integer couserid);

    List<Collect> selectAllCollect(int userid);
}
