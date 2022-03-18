package com.fight.controller;

import com.fight.exception.ShopException;
import com.fight.pojo.Collect;
import com.fight.service.CollectService;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;
    /*根据商品id收藏商品*/
    @ResponseBody
    @RequestMapping(value = "/collectPro")
    public Object collectPro(Collect collect){
       if (collectService.collectPro(collect)==null){
           return new ShopException(new ResultVo(100,"收藏失败"));
       }else return new ResultVo(200,"收藏成功");
    }
    /*根据商品id取消收藏*/
    @ResponseBody
    @RequestMapping(value = "/giveColPro")
    public Object giveColPro(Integer commid,Integer couserid){
        if (collectService.giveColPro( commid,couserid)==0){
            return new ShopException(new ResultVo(100,"取消收藏失败"));
        }else return new ResultVo(200,"取消收藏成功");

    }
    /*根据用户id查询所有收藏商品*/


}
