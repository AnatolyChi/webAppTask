package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OutUser implements Command {

    private static final String MAIN_PAGE = "controller?command=MAIN_PAGE";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(false).invalidate();
        resp.sendRedirect(MAIN_PAGE);
    }
}
