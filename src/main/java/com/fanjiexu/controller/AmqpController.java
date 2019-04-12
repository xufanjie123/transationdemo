package com.fanjiexu.controller;

import com.fanjiexu.config.AmqpConfig;
import com.fanjiexu.config.AppConfig;
import com.fanjiexu.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AmqpController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendRabbit/{message}/{queue}")
    public String send(@PathVariable String message, @PathVariable int queue) {
        User user = new User();
        user.setName(message);
        user.setBirthDay(new Date());
        rabbitTemplate.setReturnCallback((msg, i, s, s1, s2) -> {
            System.out.println("sender return success" + msg.toString() + "===" + i + "===" + s1 + "===" + s2);
        });
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("HelloSender 消息发送成功 ");
                rabbitTemplate.convertAndSend(AmqpConfig.DIRECT_EXCHANGE, queue == 1 ? AmqpConfig.ROUTEKEY1 : AmqpConfig.ROUTEKEY2, user);
            }
        });
        rabbitTemplate.convertAndSend(AmqpConfig.DIRECT_EXCHANGE, queue == 1 ? AmqpConfig.ROUTEKEY1 : AmqpConfig.ROUTEKEY2, user);
        return message;
    }

}
