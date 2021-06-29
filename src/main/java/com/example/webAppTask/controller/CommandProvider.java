package com.example.webAppTask.controller;

import com.example.webAppTask.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
        commands.put(CommandName.AUTHORIZATION_USER,    new AuthorizationUser());
        commands.put(CommandName.REGISTERED_USERS,      new GoToUsersList());
        commands.put(CommandName.UNKNOWN_COMMAND,       new UnknownCommand());
        commands.put(CommandName.AUTHORIZATION,         new GoToAuthorizationPage());
        commands.put(CommandName.REGISTRATION,          new GoToRegistrationPage());
        commands.put(CommandName.OUT_USER,              new OutUser());
    }

    public Command findCommand(String name) {
        if (name == null) {
            name = CommandName.UNKNOWN_COMMAND.toString();
        }

        CommandName commandName;
        try {
            commandName = CommandName.valueOf(name);
        } catch (IllegalArgumentException e) {
            commandName = CommandName.UNKNOWN_COMMAND;
        }

        return commands.get(commandName);
    }
}
