package com.fanjiexu.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.jms.Destination;

@Service
public class CustomerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;


    public void sendMsg(String queueName, String message) {

        Destination destination = new ActiveMQQueue(queueName);
        jmsTemplate.convertAndSend(destination, message);

    }

    @Transactional
    @JmsListener(destination = "coustomer:msg1:new",containerFactory = "msgFactory")
    public void handle(String msg){
        System.out.println(String.format("Get msg:%s",msg));
        String reply = "reply1-" + msg;
        jmsTemplate.convertAndSend("coustomer:msg1:reply",reply);
        if(msg.contains("error")){
            makeError();
        }
    }

    private void makeError(){
        throw new RuntimeException("Some Data Error");
    }

    @JmsListener(destination = "coustomer:msg2:new",containerFactory = "msgFactory")
    public void handleInCode(String msg){
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus ts = transactionManager.getTransaction(def);
        try{
            String reply = "reply1-" + msg;
            jmsTemplate.convertAndSend("coustomer:msg1:reply",reply);
            if(msg.contains("error")){
                makeError();
            }
            transactionManager.commit(ts);
        }catch (Exception e){
            transactionManager.rollback(ts);
            throw e;
        }
    }

    @JmsListener(destination = "queue-test")
    public void handle2(String msg){
        System.out.println(String.format("Get msg:%s",msg));
    }
}
