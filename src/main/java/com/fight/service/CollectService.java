package com.fight.service;

import com.fight.pojo.Collect;

public interface CollectService {
    /*收藏商品*/
    public Integer collectPro(Collect collect);

    Integer giveColPro(Integer commid,Integer couserid);
}
