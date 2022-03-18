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

import java.util.Date;
import java.util.List;

@SpringBootTest
public  class FightApplicationTests {
    @Autowired
    private testService testService;

    @Test
    public void a(){
        String orderCode = OrderCodeUtils.createOrderCode(new Date());
        System.out.println(orderCode);
    }
}

