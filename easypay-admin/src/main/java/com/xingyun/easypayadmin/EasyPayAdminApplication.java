package com.xingyun.easypayadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.xingyun.easypayadmin", "com.xingyun.easypaycommon"})
@MapperScan("com.xingyun.easypaycommon.dao.mapper")
@EnableTransactionManagement
public class EasyPayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyPayAdminApplication.class, args);
    }

}
