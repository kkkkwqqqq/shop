package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.exception.ShopException;
import com.fight.pojo.Collect;
import com.fight.service.CollectService;
import com.fight.util.JwtUtil;
import com.fight.vo.ResultVo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class CollectController {
    @Autowired
    private CollectService collectService;

    /*根据商品id收藏商品*/
    @ResponseBody
    @RequestMapping(value = "/collectPro")
    public Object collectPro(HttpServletRequest request, Collect collect) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        if (collect.getCouserid() == Integer.parseInt(userId)) {
            return new ResultVo(Constants.FAILED_STATUS, "收藏失败,自己不能收藏自己的商品");
        }
        //不能重复收藏统一商品  根据商品id和用户id 查询是否有此收藏

        Collect collect1 = collectService.existOrNot(collect.getCommid(), Integer.parseInt(userId));

        if (collect1 != null) {
            return new ResultVo(Constants.FAILED_STATUS, "不能重复添加此商品");
        }
        collect.setCouserid(Integer.parseInt(userId));
        if (collectService.collectPro(collect) == null) {
            return new ResultVo(Constants.FAILED_STATUS, "收藏失败");
        } else return new ResultVo(Constants.SUCCESS_STATUS, "收藏成功");
    }

    /*根据商品id取消收藏*/
    @ResponseBody
    @RequestMapping(value = "/giveColPro")
    public Object giveColPro(Integer commid, Integer couserid) {
        if (collectService.giveColPro(commid, couserid) == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "取消收藏失败");
        } else return new ResultVo(Constants.SUCCESS_STATUS, "取消收藏成功");

    }

    /*根据用户id查询所有收藏商品*/
    @ResponseBody
    @RequestMapping(value = "/selectAllCollect")
    public Object selectAllCollect(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        int userid = Integer.parseInt(userId);
        List<Collect> collects = collectService.selectAllCollect(userid);
        System.out.println(collects.get(0).getCollecttime());
        return  collects;
    }
}
