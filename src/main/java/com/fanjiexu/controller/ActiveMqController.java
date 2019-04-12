package com.fanjiexu.controller;

import com.fanjiexu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

@RestController
public class ActiveMqController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/listener")
    public void activemqQueueProducer(@RequestParam(name = "msg") String message) throws JMSException {
        customerService.sendMsg("coustomer:msg1:new", message);
    }

    @RequestMapping("/direct")
    public void direct(@RequestParam(name = "msg") String message){
        customerService.handle(message);
    }

    @RequestMapping("/message")
    public String message(){
        jmsTemplate.setReceiveTimeout(2000);
        Object obj = jmsTemplate.receiveAndConvert("coustomer:msg1:reply");
        return String.valueOf(obj);
    }

    @RequestMapping("/directByCode")
    public void directByCode(@RequestParam(name = "msg") String message){
        customerService.handleInCode(message);
    }

    @RequestMapping("/listenerByCode")
    public void listenerByCode(@RequestParam(name = "msg") String message) throws JMSException {
        customerService.sendMsg("coustomer:msg2:new", message);
    }
}
