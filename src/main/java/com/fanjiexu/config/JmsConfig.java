package com.fanjiexu.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;

@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    PlatformTransactionManager transactionManager(ConnectionFactory cf) {
        return new JmsTransactionManager(cf);
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory cf) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(cf);
        return jmsTemplate;
    }

    @Bean
    JmsListenerContainerFactory msgFactory(ConnectionFactory cf, PlatformTransactionManager transactionManager, DefaultJmsListenerContainerFactoryConfigurer containerFactoryConfigurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        containerFactoryConfigurer.configure(factory, cf);
        factory.setReceiveTimeout(10000L);
        factory.setTransactionManager(transactionManager);
        return factory;
    }

}
