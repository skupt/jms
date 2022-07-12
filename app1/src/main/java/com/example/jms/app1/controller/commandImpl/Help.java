package com.example.jms.app1.controller.commandImpl;

import com.example.jms.app1.service.OrderService;

public class Help extends AbstractCommand {

    public Help() {
    }

    public Help(OrderService client) {
        super(client);
    }

    public Help(OrderService client, CommandArgs commandArgs) {
        super(client, commandArgs);
    }

    @Override
    public void execute() {
        System.out.println("Commands without parameters: help.");
        System.out.println("Other command has 5 fields (command, user name, good type, volume, number), example:");
        System.out.println("'create|Jula|ITEMS|0|15' or 'create|John|LIQUIDS|3.5|0'");
        System.out.println("Type your commands one by one (one line - one command) starting from the next line");
        System.out.print("type: ");
    }

}
