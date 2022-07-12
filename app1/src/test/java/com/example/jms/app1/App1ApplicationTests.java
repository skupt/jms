package com.example.jms.app1;

import com.example.jms.app1.config.JmsConfig;
import com.example.jms.app1.config.MsgPostProcessor;
import com.example.jms.app1.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({OrderService.class, App1Application.class, JmsConfig.class, MsgPostProcessor.class})
class App1ApplicationTests {
    @Autowired
    private OrderService orderSender;

    @Test
    void contextLoads() {
    }
}
