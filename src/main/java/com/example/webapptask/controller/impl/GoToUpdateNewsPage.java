package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToUpdateNewsPage implements Command {

    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String UPDATE_NEWS_PAGE = "/WEB-INF/view/updateNews.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(TITLE_PARAM, req.getParameter(TITLE_PARAM));
        req.setAttribute(CONTENT_PARAM, req.getParameter(CONTENT_PARAM));

        req.getRequestDispatcher(UPDATE_NEWS_PAGE).forward(req, resp);
    }
}
