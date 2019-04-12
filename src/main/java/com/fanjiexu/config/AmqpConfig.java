package com.fanjiexu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    public final static String QUEUE1 = "QUEUE_1";
    public final static String QUEUE2 = "QUEUE_2";

    public final static String ROUTEKEY1 = "ROUTEKEY_1";
    public final static String ROUTEKEY2 = "ROUTEKEY_2";

    public final static String DIRECT_EXCHANGE = "DIRECT_EXCHANGE";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding binding() {

        return BindingBuilder.bind(queue1()).to(defaultExchange()).with(ROUTEKEY1);
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queue2()).to(defaultExchange()).with(ROUTEKEY2);
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setReturnCallback((msg, i, s, s1, s2) -> {
//            System.out.println("sender return success" + msg.toString() + "===" + i + "===" + s1 + "===" + s2);
//        });
//        template.setConfirmCallback((correlationData, ack, cause) -> {
//            if (!ack) {
//                System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
//            } else {
//                System.out.println("HelloSender 消息发送成功 ");
//                template.convertAndSend(correlationData);
//            }
//        });
//    }

}
