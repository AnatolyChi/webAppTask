package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToPersonalPage implements Command {
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personalPage.jsp";
    private static final String EDIT_PARAM = "edit";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(EDIT_PARAM, req.getParameter(EDIT_PARAM));
        req.getRequestDispatcher(PERSONAL_PAGE).forward(req, resp);
    }
}
