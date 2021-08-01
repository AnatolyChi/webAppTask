package com.example.webAppTask.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final String COMMAND_REQUEST_PARAM = "command";
    private final CommandProvider PROVIDER = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(COMMAND_REQUEST_PARAM);
        Command command = PROVIDER.findCommand(commandName);
        command.execute(req, resp);
    }
}
