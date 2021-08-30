package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class DeleteNews implements Command {
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNewsService();

    private static final String NEWS_ID = "news_id";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int news_id = Integer.parseInt(req.getParameter(NEWS_ID));

        try {
            NEWS_SERVICE.deleteNews(news_id);
            resp.sendRedirect(req.getHeader(REFERER));
        } catch (ServiceException e) {
            log.error("error while deleting");
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
