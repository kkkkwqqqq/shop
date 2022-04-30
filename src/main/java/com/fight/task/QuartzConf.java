package com.fight.task;

import com.fight.mapper.UserMapper;
import com.fight.service.CommodityService;
import com.fight.service.UserService;
import com.fight.util.JwtUtil;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 定时任务 刷新浏览数
 */
@Component
public class QuartzConf {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    /*每天的两点35分执行一次*/
    @Scheduled(cron = "0 35 2 * * ?")
    public void updateViews() throws Exception {
        Set<String> keyset = redisTemplate.keys("*");
        System.out.println("更新浏览量");
        System.out.println(keyset);
        for (String key : keyset) {   //遍历keys 拿到浏览量
            String liulan = (String) redisTemplate.opsForValue().get(key);
            commodityService.updateLiulanLBycomid(Integer.parseInt(key), Integer.parseInt(liulan));
        }
    }

}




