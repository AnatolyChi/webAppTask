package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddNewsPage implements Command {

    private static final String ADD_NEWS_PAGE = "/WEB-INF/view/add_news.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_NEWS_PAGE).forward(req, resp);
    }
}
