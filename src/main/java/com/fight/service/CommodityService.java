package com.fight.service;

import com.fight.pojo.Commimages;
import com.fight.pojo.Commodity;
import io.swagger.models.auth.In;

import java.util.List;

public interface CommodityService {
    /*找出最新的12条商品*/
    List<com.fight.pojo.Commodity> selectNew();
        /*按商品类别进行分类*/
    List<Commodity> selectByType(String type,Integer start,Integer end);


    /*按商品类别和价格从高到底排序 */
    List<Commodity> selectByTypeAndPriceDesc(String type,Integer start,Integer end);

    /*按商品类别和价格从低到高排序 */
    List<Commodity> selectByTypeAndPriceAsc(String type,Integer start,Integer end);

/*根据商品id查询指定商品*/
    List<Commodity> selectProById(Integer userid);

    public  Integer updateMyProByid(Commodity commodity);

    public Integer deleteProByid(Integer commid,Integer userid);

    List<Commodity> selectweishen(Commodity commodity);

    List<Commodity> selectAllPro();

    List<Commodity> selectweishenPro(Integer status);

    Integer publishCommodity(Commodity commodity);

    Commodity selectProBynameAnduserId(String commname, Integer userid);

    List<Commodity> select();

    void updateLiulanLBycomid(Integer key, Integer liulan);

    List<Commodity> selectProById1(Integer commid);

    Commodity selectProByIdAndCid(Integer commid, Integer userid);


    Integer deleteProByid1(Integer commid);

    Integer updateMyProByAdmin(Integer commid);
}
