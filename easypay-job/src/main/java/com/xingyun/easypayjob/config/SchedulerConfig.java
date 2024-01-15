package com.xingyun.easypayjob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(this.buildSchedulerThreadPool());
    }

    /**
     * Spring的@Scheduled的自定义周期性线程池
     * @return
     */
    @Bean
    public ExecutorService buildSchedulerThreadPool() {

        ScheduledExecutorService threadPoolExecutor = new ScheduledThreadPoolExecutor(8,Executors.defaultThreadFactory());

        return threadPoolExecutor;
    }
}
