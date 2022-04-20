package com.fight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;
     @RequestMapping("/test")
    public String a(){
         redisTemplate.opsForValue().set("user","user");
         return  (String) redisTemplate.opsForValue().get("key");
     }
}
