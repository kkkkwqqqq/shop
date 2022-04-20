package com.fight;

import com.fight.mapper.testMapper;
import com.fight.pojo.Commodity;
import com.fight.service.CommodityService;
import com.fight.service.testService;

import com.fight.util.OrderCodeUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@SpringBootTest
public  class FightApplicationTests {
    @Autowired
    private testService testService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void a(){
            // redisTemplate 操作不同的数据类型 api和我们指令一样
            //opsForValue   操作字符串 类似string
            //opsForList   操作List 类似List   ...其他数据类型类比

            //除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作 比如事务，和基本的CRUD
            //    redisTemplate.multi();
            //  redisTemplate.delete("1");


            //获取redis的连接对象
            //   RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            // connection.flushDb();
            // connection.flushAll();

            redisTemplate.opsForValue().set("user","user");
            System.out.println(redisTemplate.opsForValue().get("user"));

        }
    }


