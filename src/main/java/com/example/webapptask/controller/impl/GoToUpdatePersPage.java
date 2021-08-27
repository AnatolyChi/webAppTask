package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToUpdatePersPage implements Command {
    private static final String UPDATE_PERSONAL_PAGE = "/WEB-INF/view/update_personal_page.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UPDATE_PERSONAL_PAGE).forward(req, resp);
    }
}
