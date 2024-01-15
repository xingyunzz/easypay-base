package com.xingyun.easypayapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.xingyun.easypayapi", "com.xingyun.easypaycommon"})
@MapperScan("com.xingyun.easypaycommon.dao.mapper")
@EnableTransactionManagement
@EnableScheduling
public class EasyPayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.xingyun.easypayapi.EasyPayApiApplication.class, args);
    }

}
