package com.fight.controller;

import com.fight.pojo.soldrecord;
import com.fight.service.OrderService;
import com.fight.service.SoldrecordService;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResultVo(200, "添加售出记录成功");
        } else {
            return new ResultVo(100, "添加售出记录失败");
        }
    }

    //传入售出记录id，， 修改发货状态
    @RequestMapping("/updateStatus")
    public ResultVo updateStatus(soldrecord soldrecord) {
        // 去数据库中修改此id对应的订单状态
        soldrecord.setFahuostatus("已发货");
        Integer count = soldrecordService.updateStatus(soldrecord);
        if (count == 1) {
            return new ResultVo(200, "发货成功");
        } else {
            return new ResultVo(100, "发货失败");
        }
    }

    //查看售出记录
    @RequestMapping("/selectSoldrecordByuserid")
    public ResultVo selectSoldrecord(Integer userid,int page,int count) {
        List<soldrecord> soldrecords = soldrecordService.selectSoldrecordByuserid(userid,page,count);
            return new ResultVo(200, "发货成功",soldrecords);
        }
    }



