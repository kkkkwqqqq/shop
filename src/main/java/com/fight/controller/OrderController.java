package com.fight.controller;

import com.fight.pojo.Order;
import com.fight.service.OrderService;
import com.fight.util.JustPhone;
import com.fight.util.OrderCodeUtils;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    //添加订单
    @RequestMapping("/addOrder")
    public ResultVo addOrder(Order order) {
        //生成订单编号
        String orderCode = OrderCodeUtils.createOrderCode(new Date());
        //验证手机号码是否合法
        String buyerphone = order.getBuyerphone();
        if (!JustPhone.isChinaPhoneLegal(buyerphone)) {
            return new ResultVo(100, "手机号码不合法，请重新输入");
        }
        order.setOrderid(orderCode);
        order.setKuaidibianhao("待发货");
        System.out.println(order);
        Integer count = orderService.addOrder(order);
        if (count == 1) {
            return new ResultVo(200, "添加订单成功");
        } else {
            return new ResultVo(100, "添加订单失败");
        }

    }

    // 点击确认收货 发货状态对应改为已确认收货
    //传入商品id，买家id，填写快递编号 修改发货状态
    @RequestMapping("/confirmGood")
    public ResultVo updateStatus(Order order) {
        // 去数据库中修改此id对应的订单状态
        order.setStatus("已经确认收货");
        Integer count = orderService.confirmGood(order);
        if (count == 1) {
            return new ResultVo(200, "已确定收货");
        } else {
            return new ResultVo(100, "确定收货失败");
        }
    }
}
