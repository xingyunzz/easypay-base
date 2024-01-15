package com.xingyun.easypayjob.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class MQEventSender {

    private Logger logger = LoggerFactory.getLogger(MQEventSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    public void sendEvent(String event, String msg) {
        threadPoolExecutor.submit(() -> {
            try {
                logger.info("contract event send: {},{}", event, msg);
                rabbitTemplate.send(event, new Message(msg.getBytes(StandardCharsets.UTF_8)));
            } catch (AmqpException e) {
                logger.error("MQ发送消息失败,{}:{}", event, msg);
            }
        });
    }

    public void sendDelayEvent(String event, String msg, long delayedMills) {

        threadPoolExecutor.submit(() -> {
            try {
                MessageProperties properties = MessagePropertiesBuilder.newInstance().setHeader("x-delay", delayedMills).build();

                logger.info("发送延迟消息消息：{},{}", event, msg);
                rabbitTemplate.send("delay.direct",
                        event, new Message(msg.getBytes(StandardCharsets.UTF_8), properties));
            } catch (AmqpException e) {
                logger.error("MQ发送延迟消息失败,{},{},{}", event, msg, delayedMills);
            }
        });

    }
}
