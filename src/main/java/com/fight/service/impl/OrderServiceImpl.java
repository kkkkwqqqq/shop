package com.fight.service.impl;

import com.fight.mapper.OrderMapper;
import com.fight.pojo.Order;
import com.fight.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Integer addOrder(Order order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public Integer updateStatus(Order order) {
        return orderMapper.updateStatus(order);
    }

    @Override
    public Integer confirmGood(Order order) {
        return orderMapper.confirmGood( order);
    }

    @Override
    public Integer updateStatus1(int status, String orderid) {
        return orderMapper.updateStatus1(status,orderid);
    }

    @Override
    public Integer deleteOrderById(String userId, String orderid) {
        return orderMapper.deleteOrderByid(userId,orderid);
    }

    @Override
    public Integer deliveryGood(String orderid, int sellerid, String status) {
        return orderMapper.deliveryGood(orderid,sellerid,status);
    }

    @Override
    public Integer updateStatus2(Order order) {
        return orderMapper.updateStatus2(order.getStatus(),order.getOrderid());
    }
}
