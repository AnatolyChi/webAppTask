package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToUpdateNewsPage implements Command {

    private static final String NEWS_ID_PARAM = "news_id";
    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String UPDATE_NEWS_PAGE = "/WEB-INF/view/update_news.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(NEWS_ID_PARAM, req.getParameter(NEWS_ID_PARAM));
        req.setAttribute(TITLE_PARAM, req.getParameter(TITLE_PARAM));
        req.setAttribute(CONTENT_PARAM, req.getParameter(CONTENT_PARAM));

        req.getRequestDispatcher(UPDATE_NEWS_PAGE).forward(req, resp);
    }
}