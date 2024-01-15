package com.xingyun.easypayjob;

import com.xingyun.easypayjob.common.EnvHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.xingyun.easypayjob","com.xingyun.easypaycommon"})
@MapperScan("com.xingyun.easypaycommon.dao.mapper")
@EnableTransactionManagement
@EnableScheduling
public class EasyPayJobApplication {

	public static void main(String[] args) {
		SimpleCommandLinePropertySource simpleCommandLinePropertySource = new SimpleCommandLinePropertySource(args);
		EnvHolder.SPRING_ENV = simpleCommandLinePropertySource.getProperty("spring.profiles.active");
		SpringApplication.run(EasyPayJobApplication.class, args);
	}

}
