package com.fanjiexu.service;

import com.alibaba.fastjson.JSONObject;
import com.fanjiexu.config.AmqpConfig;
import com.fanjiexu.model.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RabbitListener(queues = AmqpConfig.QUEUE1)
public class AmqpService1 {
    @RabbitHandler
    public void process(User msg, Channel channel, Message message) throws IOException {
        System.out.println(AmqpConfig.QUEUE1 + JSONObject.toJSONString(msg));
        if(msg.getName().contains("error")) {
            throw new RuntimeException("12321");
        }
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
