package com.fight.service;
import com.fight.pojo.Order;

public interface OrderService {
    //添加订单
    public Integer addOrder(Order order);

    public  Integer updateStatus(Order order);

    //确认收获
    public Integer confirmGood(Order order);

    Integer updateStatus1(int status, String orderid);

    Integer deleteOrderById(String userId, String orderid);

    Integer deliveryGood(String orderid, int sellerid, String status);



    Integer updateStatus2(Order order);
}
