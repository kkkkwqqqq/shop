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
}
