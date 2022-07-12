package com.example.app1.controller.commandImpl;

import com.example.app1.pojo.GoodsType;
import com.example.app1.pojo.Order;
import com.example.app1.pojo.User;
import com.example.app1.service.OrderService;

public class ThrowWhileCreating extends AbstractCommand {
    public ThrowWhileCreating() {
    }

    public ThrowWhileCreating(OrderService client) {
        super(client);
    }

    public ThrowWhileCreating(OrderService client, CommandArgs commandArgs) {
        super(client, commandArgs);
    }

    @Override
    public void execute() {
        User user = new User(commandArgs.getUserName());
        GoodsType goodsType = GoodsType.valueOf(commandArgs.getGoodsType());
        float volume = commandArgs.getVolume();
        int number = commandArgs.getNumber();
        Order order = new Order(user, goodsType, volume, number);
        client.send(order);
        System.out.println("Order is sent");
        System.out.print("type: ");
    }
}
