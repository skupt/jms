package com.example.jms.app2.service;

import com.example.jms.app1.pojo.Order;
import com.example.jms.app2.pojo.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessingService.class);
    private static final String SUMMARY_TOPIC = "orders.summary";

    @Autowired
    private Summary summary;

    public JmsResponse<Message<Summary>> processItemsMessage(Order order) {
        if ((summary.getSummaryItems().getAcceptedVolume() + order.getTotal()) > summary.getSummaryItems().getAllowedVolume()) {
            //reject
            int alreadyRejectedItems = summary.getSummaryItems().getRejected();
            double alreadyRejectedVolume = summary.getSummaryItems().getRejectedVolume();
            summary.getSummaryItems().setRejected(++alreadyRejectedItems);
            summary.getSummaryItems().setRejectedVolume(alreadyRejectedVolume + order.getTotal());
        } else {
            int alreadyAcceptedItems = summary.getSummaryItems().getAccepted();
            double alreadyAcceptedVolume = summary.getSummaryItems().getAcceptedVolume();
            summary.getSummaryItems().setAccepted(++alreadyAcceptedItems);
            summary.getSummaryItems().setAcceptedVolume(alreadyAcceptedVolume + order.getTotal());
        }
        LOGGER.info(summary.toString());
        Message<Summary> summaryMessage = MessageBuilder.withPayload(summary).build();

        return JmsResponse.forQueue(summaryMessage, SUMMARY_TOPIC);
    }

    public JmsResponse<Message<Summary>> processLiquidsMessage(Order order) {
        if ((summary.getSummaryLiquids().getAcceptedVolume() + order.getTotal()) > summary.getSummaryLiquids().getAllowedVolume()) {
            int alreadyRejectedLiquids = summary.getSummaryLiquids().getRejected();
            double alreadyRejectedVolume = summary.getSummaryLiquids().getRejectedVolume();
            summary.getSummaryLiquids().setRejected(++alreadyRejectedLiquids);
            summary.getSummaryLiquids().setRejectedVolume(alreadyRejectedVolume + order.getTotal());
        } else {
            int alreadyAcceptedLiquids = summary.getSummaryLiquids().getAccepted();
            double alreadyAcceptedVolume = summary.getSummaryLiquids().getAcceptedVolume();
            summary.getSummaryLiquids().setAccepted(++alreadyAcceptedLiquids);
            summary.getSummaryLiquids().setAcceptedVolume(alreadyAcceptedVolume + order.getTotal());
        }
        LOGGER.info(summary.toString());
        Message<Summary> summaryMessage = MessageBuilder.withPayload(summary).build();
        return JmsResponse.forQueue(summaryMessage, SUMMARY_TOPIC);
    }


    public Summary backupSummary() {
        Summary backup = null;
        try {
            backup = (Summary) summary.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Summary cloning exception", e.getCause());
        }
        return backup;
    }
}
