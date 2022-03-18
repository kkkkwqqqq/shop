package com.fight.service;
import com.fight.pojo.Order;

public interface OrderService {
    //添加订单
    public Integer addOrder(Order order);

    public  Integer updateStatus(Order order);

    //确认收获
    public Integer confirmGood(Order order);
}
