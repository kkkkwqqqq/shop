package com.fight.mapper;

import com.fight.pojo.Commodity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer commid,Integer userid);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer commid);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);



   /*按商品类别进行分类*/
    List<Commodity> selectByType(@Param("type")String type, @Param("start")Integer start, @Param("end")Integer end);

    /*按商品类别和价格从高到底排序 参数1代表从高到低 0代表从低到高*/
    /*价格从高到低*/
    List<Commodity> selectByTypeAndPriceDesc(String type,Integer start,Integer end);
/*价格从低到高*/
    List<Commodity> selectByTypeAndPriceAsc(String type,Integer start,Integer end);

   List<Commodity>  selectByuserId(Integer userid);

    List<Commodity> selectweishen(Commodity commodity);

    List<Commodity> selectAllPro();

    List<Commodity> selectweiPro(Integer status);

    Commodity selectProBynameAnduserId(String commname, Integer userid);

    List<Commodity> selectNew();

    void updateLiulanLBycomid(Integer key, Integer liulan);

    List<Commodity> selectByuserId1(Integer commid);

    Commodity selectProByIdAndCid(Integer commid, Integer userid);


    Integer deleteByCommid(Integer commid);

    Integer updateMyProByAdmin(Integer commid);
}
