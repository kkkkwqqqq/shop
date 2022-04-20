package com.fight.mapper;

import com.fight.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateStatus(Order order);

   public int confirmGood(Order order);

    Integer updateStatus1(int status, String orderid);

    Integer deleteOrderByid(String buyerid, String orderid);

    Integer deliveryGood(String orderid, int sellerid, String status);

    Integer updateStatus2(String status,String orderid);
}
