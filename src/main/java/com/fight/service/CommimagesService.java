package com.fight.service;

import com.fight.pojo.Commimages;

import java.util.List;

public interface CommimagesService {
    /*查询商品的图片 3张*/
    List<Commimages> selectImageBy(Integer commid);

    Integer updateCommimages(Commimages commimages);

    Integer insertAllCommimages(Commimages commimages);

    Integer deleteImagByid(int id);
}
