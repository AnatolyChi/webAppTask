package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToNewsPage implements Command {
    private static final String NEWS_PARAM = "news";
    private static final String ADD_NEWS_PAGE = "/WEB-INF/views/addNews.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(NEWS_PARAM, NEWS_PARAM);
        req.getRequestDispatcher(ADD_NEWS_PAGE).forward(req, resp);
    }
}
