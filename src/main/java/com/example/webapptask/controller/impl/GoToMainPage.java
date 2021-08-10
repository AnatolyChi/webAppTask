package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GoToMainPage implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final NewsService newsService = provider.getNEWS_SERVICE();

    private static final String NEWS_LIST_PARAM = "newsList";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> newsList = new ArrayList<>();

        try {
            newsList = newsService.newsList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute(NEWS_LIST_PARAM, newsList);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
