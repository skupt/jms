package com.example.app1.controller;


import com.example.app1.controller.commandImpl.CommandArgs;

public interface Command {
    void execute();

    void setCommandArgs(CommandArgs commandArgs);

    CommandArgs getCommandArgs();
}
