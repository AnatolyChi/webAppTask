package com.example.webAppTask.controller.impl;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.NewsService;
import com.example.webAppTask.service.exception.ServiceException;
import com.example.webAppTask.service.ServiceProvider;
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
    private static final NewsService newsService = provider.getNewsService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> newsList = new ArrayList<>();

        try {
            newsList = newsService.newsList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("newsList", newsList);
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }
}
