package com.example.jms.app1.service;

import com.example.jms.app1.config.MsgPostProcessor;
import com.example.jms.app1.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MsgPostProcessor msgPostProcessor;

    public void send(Order order) {
        msgPostProcessor.setOrder(order);
        jmsTemplate.convertAndSend(order, msgPostProcessor);
    }
}
