package com.example.app1.config;

import com.example.app1.pojo.GoodsType;
import com.example.app1.pojo.Order;
import lombok.Data;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Data
@Component
public class MsgPostProcessor implements MessagePostProcessor {
    private Order order;

    @Override
    public Message postProcessMessage(Message message) throws JMSException {
        switch (order.getGoodsType()) {
            case ITEMS:
                message.setStringProperty("GOODS_TYPE", GoodsType.ITEMS.name());
                break;
            case LIQUIDS:
                message.setStringProperty("GOODS_TYPE", GoodsType.LIQUIDS.name());
                break;
            case THROW:
                message.setStringProperty("GOODS_TYPE", GoodsType.THROW.name());
                break;
        }
        return message;
    }
}