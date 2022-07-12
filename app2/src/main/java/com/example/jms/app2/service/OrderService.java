package com.example.jms.app2.service;

import com.example.jms.app1.pojo.Order;
import com.example.jms.app2.pojo.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderProcessingService orderProcessingService;

    @Autowired
    Summary summary;

    @JmsListener(destination = "orders", selector = "GOODS_TYPE = 'ITEMS'")
    public JmsResponse<Message<Summary>> handleItem(@Payload Order order) {
        JmsResponse<Message<Summary>> response = null;
        Summary backup = orderProcessingService.backupSummary();
        try {
            response = orderProcessingService.processItemsMessage(order);
        } catch (Exception e) {
            LOGGER.warn(e + ": " + e.getMessage() + ". " + e.getCause());
            summary = backup;
            throw new RuntimeException("Exception during handling item", e);
        }
        return response;
    }

    @JmsListener(destination = "orders", selector = "GOODS_TYPE = 'LIQUIDS'")
    public JmsResponse<Message<Summary>> handleLiqiud(@Payload Order order) {
        JmsResponse<Message<Summary>> response = null;
        Summary backup = orderProcessingService.backupSummary();
        try {
            response = orderProcessingService.processLiquidsMessage(order);
        } catch (Exception e) {
            LOGGER.warn(e + ": " + e.getMessage() + ". " + e.getCause());
            summary = backup;
            throw new RuntimeException("Exception during handling item", e);
        }
        return response;
    }

    @JmsListener(destination = "orders", selector = "GOODS_TYPE = 'THROW'")
    public JmsResponse<Message<Summary>> handleItemWithException(@Payload Order order) {
        JmsResponse<Message<Summary>> response = null;
        Summary backup = orderProcessingService.backupSummary();
        try {
            response = orderProcessingService.processItemsMessage(order);
            throw new RuntimeException("SPECIAL EXCEPTION TO SHOW TRANSACTIONS");
        } catch (Exception e) {
            LOGGER.warn(e + ": " + e.getMessage() + ". " + e.getCause());
            summary = backup;
            LOGGER.info("Backuped: " + summary.toString());
            throw new RuntimeException("Exception during handling item", e);
        }
    }
}
