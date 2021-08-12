package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OutUser implements Command {
    private static final String USER_SESSION = "user";
    private static final String MAIN_PAGE_COMMAND = "/index.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(USER_SESSION);
        resp.sendRedirect(MAIN_PAGE_COMMAND);
    }
}
