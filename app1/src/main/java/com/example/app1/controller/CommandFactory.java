package com.example.app1.controller;

import com.example.app1.controller.commandImpl.CommandArgs;
import com.example.app1.controller.commandImpl.Create;
import com.example.app1.controller.commandImpl.Help;
import com.example.app1.controller.commandImpl.ThrowWhileCreating;
import com.example.app1.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);
    private static final String CMD_REGEX = "(?<cmd>[a-zA-Z]+){1}\\|?((?<name>[^\\|]+){1}\\|(?<goodsType>LIQUIDS|ITEMS|THROW){1}\\|(?<volume>[0-9.]*)\\|?(?<number>[0-9]*))?";
    private static final Set<String> simpleCmds = new HashSet<>(Arrays.asList("findall", "exit", "help"));
    private final Pattern pattern = Pattern.compile(CMD_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private OrderService client;

    public Command parseCommand() {
        Command command = null;
        while (command == null) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String cmd = matcher.group("cmd");
                String name = matcher.group("name");
                String goodsType = matcher.group("goodsType");
                String volume = matcher.group("volume");
                String number = matcher.group("number");
                Integer itemNumber = null;
                Float liquidVolume = null;
                try {
                    itemNumber = Integer.parseInt(number);
                    liquidVolume = Float.parseFloat(volume);
                } catch (NumberFormatException e) {
                    if (!simpleCmds.contains(cmd.toLowerCase())) {
                        System.out.println("Can't parse numbers correctly. Repeat command input");
                        System.out.print("type: ");
                        continue;
                    }
                }
                CommandArgs commandArgs = new CommandArgs();
                String cmdLC = cmd.toLowerCase();
                switch (cmdLC) {
                    case "help":
                        command = new Help();
                        break;
                    case "create":
                        command = new Create(client);
                        if (itemNumber != null || name != null) {
                            commandArgs.setUserName(name);
                            commandArgs.setGoodsType(goodsType);
                            commandArgs.setVolume(liquidVolume);
                            commandArgs.setNumber(itemNumber);
                            command.setCommandArgs(commandArgs);
                        }
                    case "throw":
                        command = new ThrowWhileCreating(client);
                        if (itemNumber != null || name != null) {
                            commandArgs.setUserName(name);
                            commandArgs.setGoodsType(goodsType);
                            commandArgs.setVolume(liquidVolume);
                            commandArgs.setNumber(itemNumber);
                            command.setCommandArgs(commandArgs);
                        }
                        break;
                }
                if (command != null) return command;
                System.out.println("Wrong command. Type 'help' to clear syntax or other command name.");
                System.out.print("type: ");
            }
        }
        return command;
    }
}
