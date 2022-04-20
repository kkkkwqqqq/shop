package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.exception.ShopException;
import com.fight.pojo.Order;
import com.fight.pojo.soldrecord;
import com.fight.service.OrderService;
import com.fight.service.SoldrecordService;
import com.fight.util.JwtUtil;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SoldrecordController {
    @Autowired
    private SoldrecordService soldrecordService;
    @Autowired
    private OrderService orderService;

    //生成一条售出记录
    @RequestMapping("/addSoldRecord")
    public ResultVo addSoldRecord(soldrecord soldrecord) {
        Integer count = soldrecordService.addSoldRecord(soldrecord);
        if (count == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "添加售出记录成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "添加售出记录失败");
        }
    }

    //传入售出记录id，， 修改发货状态
    @RequestMapping("/updateStatus")
    public ResultVo updateStatus(soldrecord soldrecord) {
        // 去数据库中修改此id对应的订单状态
        soldrecord.setFahuostatus("已发货");
        Integer count = soldrecordService.updateStatus(soldrecord);
        if (count == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "发货成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "发货失败");
        }
    }

    //查看售出记录
    @RequestMapping("/selectSoldrecordByuserid")
    public ResultVo selectSoldrecord(Integer userid, int page, int count) {
        List<soldrecord> soldrecords = soldrecordService.selectSoldrecordByuserid(userid, page, count);
        return new ResultVo(Constants.SUCCESS_STATUS, "发货成功", soldrecords);
    }

    //商家确认收货
    @RequestMapping("/SjconfirmGood")
    @Transactional
    public ResultVo updateStatus(HttpServletRequest request, soldrecord soldrecord) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        soldrecord.setUserid(Integer.parseInt(userId));
        Integer count = soldrecordService.updateStatus1("已收货", soldrecord.getOrderid());
        if (count == 1) {
            Order order = new Order();
            order.setOrderid(soldrecord.getOrderid());
            order.setStatus("已收货");
            Integer count1 = orderService.updateStatus2(order);
            if (count1 == 1) {
                return new ResultVo(Constants.SUCCESS_STATUS, "确认收货成功");
            }
        } else {
            throw new ShopException(Constants.FAILED_STATUS, "确认收货失败");
        }

        throw new ShopException(Constants.FAILED_STATUS, "确认收货失败");

    }

    @RequestMapping("/allSoldRecord")
    public ResultVo allSoldRecord(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        int userid = Integer.parseInt(userId);
        List<soldrecord> soldrecords = soldrecordService.allSoldRecord(userid);
        return new ResultVo(Constants.SUCCESS_STATUS,"查询售出记录成功",soldrecords);

    }
}



