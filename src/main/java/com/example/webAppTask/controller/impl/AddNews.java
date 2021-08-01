package com.example.webAppTask.controller.impl;

import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.NewsService;
import com.example.webAppTask.service.exception.ServiceException;
import com.example.webAppTask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AddNews implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final NewsService newsService = provider.getNewsService();

    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String INDEX = "/index.jsp";
    private static final String ADD_NEWS_URL = "/WEB-INF/views/addNews.jsp";
    private static final String LOG = "news are null";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(TITLE);
        String content = req.getParameter(CONTENT);

        try {
            newsService.addNews(title, content);
            req.getRequestDispatcher(INDEX).forward(req, resp);
        } catch (ServiceException e) {
            log.info(LOG);
            req.getRequestDispatcher(ADD_NEWS_URL);
        }
    }
}
