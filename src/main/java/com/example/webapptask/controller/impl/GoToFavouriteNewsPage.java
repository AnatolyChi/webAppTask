package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
public class GoToFavouriteNewsPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNewsService();

    private static final String USER_ID_PARAM = "user_id";
    private static final String NEWS_LIST_PARAM = "newsList";
    private static final String FAVOURITE_NEWS_PAGE = "/WEB-INF/view/favourite_news.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter(USER_ID_PARAM));

        try {
            List<News> newsList = NEWS_SERVICE.favouriteNewsList(userId);

            req.setAttribute(NEWS_LIST_PARAM, newsList);
            req.getRequestDispatcher(FAVOURITE_NEWS_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            log.error("error on favourite news", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
