package com.fanjiexu.service;

import com.alibaba.fastjson.JSONObject;
import com.fanjiexu.config.AmqpConfig;
import com.fanjiexu.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = AmqpConfig.QUEUE2)
public class AmqpService2 {
    @RabbitHandler
    public void process(User msg) {
        System.out.println(AmqpConfig.QUEUE2 + JSONObject.toJSONString(msg));
        if(msg.getName().contains("error")) {
            throw new RuntimeException("12321");
        }
    }
}
