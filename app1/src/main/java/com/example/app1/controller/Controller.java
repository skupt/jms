package com.example.app1.controller;


import com.example.app1.controller.commandImpl.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Controller {
    private boolean started = true;

    @Autowired
    private CommandFactory commandFactory;

    @PostConstruct
    public void start() {
        new Help().execute();
        control();
    }

    public void control() {
        while (started) {
            Command command = commandFactory.parseCommand();
            command.execute();
        }
    }
}
