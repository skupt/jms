package com.example.jms.app1.controller.commandImpl;

import com.example.jms.app1.service.OrderService;
import com.example.jms.app1.controller.Command;

public abstract class AbstractCommand implements Command {
    protected OrderService client;
    protected CommandArgs commandArgs;

    public AbstractCommand() {
    }

    public AbstractCommand(OrderService client) {
        this.client = client;
    }

    public AbstractCommand(OrderService client, CommandArgs commandArgs) {
        this.client = client;
        this.commandArgs = commandArgs;
    }

    public OrderService getClient() {
        return client;
    }

    public void setClient(OrderService client) {
        this.client = client;
    }

    public CommandArgs getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(CommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }
}
