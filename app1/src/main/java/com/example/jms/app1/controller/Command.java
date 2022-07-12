package com.example.jms.app1.controller;


import com.example.jms.app1.controller.commandImpl.CommandArgs;

public interface Command {
    void execute();
    void setCommandArgs(CommandArgs commandArgs);
    CommandArgs getCommandArgs();
}
