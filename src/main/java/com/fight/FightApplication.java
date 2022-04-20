package com.fight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@MapperScan("com.fight.mapper")
@SpringBootApplication(scanBasePackages = "com.fight")
@EnableScheduling
public class FightApplication {

    public static void main(String[] args) {
        SpringApplication.run(FightApplication.class, args);
    }

}
