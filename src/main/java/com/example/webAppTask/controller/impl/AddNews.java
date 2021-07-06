package com.example.webAppTask.controller.impl;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.NewsService;
import com.example.webAppTask.service.ServiceException;
import com.example.webAppTask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddNews implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final NewsService newsService = provider.getNewsService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String info = req.getParameter("info");
        News news = new News(title, info);

        try {
            newsService.add(news);
//            resp.sendRedirect("/controller?command=MAIN_PAGE");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (ServiceException e) {
            // log
            req.getRequestDispatcher("/WEB-INF/views/addNews.jsp");
        }
    }
}
