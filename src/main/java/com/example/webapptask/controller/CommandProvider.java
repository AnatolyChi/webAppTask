package com.example.webapptask.controller;

import com.example.webapptask.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> COMMANDS = new HashMap<>();

    public CommandProvider() {
        COMMANDS.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
        COMMANDS.put(CommandName.UPDATE_PERSONAL_PAGE,  new GoToUpdatePersPage());
        COMMANDS.put(CommandName.AUTHORIZATION_USER,    new AuthorizationUser());
        COMMANDS.put(CommandName.UPDATE_NEWS_PAGE,      new GoToUpdateNewsPage());
        COMMANDS.put(CommandName.UNKNOWN_COMMAND,       new UnknownCommand());
        COMMANDS.put(CommandName.AUTHORIZATION,         new GoToAuthorizationPage());
        COMMANDS.put(CommandName.PERSONAL_PAGE,         new GoToPersonalPage());
        COMMANDS.put(CommandName.CHANGE_LOCALE,         new ChangeLocale());
        COMMANDS.put(CommandName.REGISTRATION,          new GoToRegistrationPage());
        COMMANDS.put(CommandName.UPDATE_USER,           new UpdateUser());
        COMMANDS.put(CommandName.UPDATE_NEWS,           new UpdateNews());
        COMMANDS.put(CommandName.DELETE_NEWS,           new DeleteNews());
        COMMANDS.put(CommandName.MAIN_PAGE,             new GoToMainPage());
        COMMANDS.put(CommandName.NEWS_PAGE,             new GoToNewsPage());
        COMMANDS.put(CommandName.OUT_USER,              new OutUser());
        COMMANDS.put(CommandName.ADD_NEWS,              new AddNews());
        COMMANDS.put(CommandName.NEWS,                  new GoToAddNewsPage());
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

        return COMMANDS.get(commandName);
    }
}
