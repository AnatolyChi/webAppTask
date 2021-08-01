package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLocal implements Command {
    private static final String LOCAL = "local";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(LOCAL, req.getParameter(LOCAL));
        resp.sendRedirect(req.getHeader("referer"));
    }
}