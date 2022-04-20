package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.exception.ShopException;
import com.fight.pojo.Order;
import com.fight.pojo.soldrecord;
import com.fight.service.OrderService;
import com.fight.service.SoldrecordService;
import com.fight.util.JustPhone;
import com.fight.util.JwtUtil;
import com.fight.util.OrderCodeUtils;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
   @Autowired
   private SoldrecordService soldrecordService;

    //添加订单
    @RequestMapping("/addOrder")
    public ResultVo addOrder(Order order) {
        //生成订单编号
        String orderCode = OrderCodeUtils.createOrderCode(new Date());
        //验证手机号码是否合法
        String buyerphone = order.getBuyerphone();
        if (!JustPhone.isChinaPhoneLegal(buyerphone)) {
            return new ResultVo(Constants.FAILED_STATUS, "手机号码不合法，请重新输入");
        }
        order.setOrderid(orderCode);
        order.setKuaidibianhao("待发货");
        System.out.println(order);
        Integer count = orderService.addOrder(order);
        if (count == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "添加订单成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "添加订单失败");
        }

    }

    //用户删除订单
    @RequestMapping("/deleteOrder")
    public ResultVo deletyOrderById(HttpServletRequest request,Order order) {
        //校验是否是本人操作  如果是本人，运行删除
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        order.setBuyerid(Integer.parseInt(userId));
        Integer count = orderService.deleteOrderById(userId, order.getOrderid());

        if (count==1){
            soldrecordService.deleteRecordById(order.getOrderid());
            return new ResultVo(Constants.SUCCESS_STATUS,"删除订单成功");
        }else {
            return new ResultVo(Constants.FAILED_STATUS,"删除订单失败");
        }


    }



    // 点击确认收货 发货状态对应改为已确认收货
    //传入订单id，买家id， 修改发货状态
    @RequestMapping("/confirmGood")
    @Transactional
    public ResultVo updateStatus(HttpServletRequest request,Order order) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        order.setBuyerid(Integer.parseInt(userId));

        // 去数据库中修改此id对应的订单状态
        order.setStatus("已收货");
        Integer count = orderService.confirmGood(order);
        if (count == 1) {
            Integer count1 = soldrecordService.updateStatus1("已收货",order.getOrderid());
            if (count1 == 1) {
                return new ResultVo(Constants.SUCCESS_STATUS, "已确定收货");
            } else {
                throw new ShopException(Constants.FAILED_STATUS, "确认收货失败");

            }
        }    throw new ShopException(Constants.FAILED_STATUS, "确认收货失败");
    }

    //商家发货
    @RequestMapping("/deliveryGood")
    @Transactional
    public ResultVo deliveryGood(HttpServletRequest request,String orderid) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        int sellerid = Integer.parseInt(userId);

        // 去数据库中修改此id对应的订单状态 改为已发货

        Integer count = orderService.deliveryGood(orderid,sellerid,"已发货");
        if (count == 1) {
            Integer count1 = soldrecordService.updateStatus1("已发货", orderid);
            if (count1==0){
                throw new ShopException(Constants.FAILED_STATUS,"修改状态失败");
            }
            return new ResultVo(Constants.SUCCESS_STATUS, "已确定发货");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "确认发货失败");
        }
    }

}
