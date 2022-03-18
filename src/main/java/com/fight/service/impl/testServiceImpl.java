package com.fight.service.impl;

import com.fight.mapper.testMapper;
import com.fight.pojo.test;
import com.fight.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testServiceImpl implements testService {
    @Autowired
    private testMapper testMapper1;
    public List<test> all(){
       return testMapper1.test1();
    }
}
