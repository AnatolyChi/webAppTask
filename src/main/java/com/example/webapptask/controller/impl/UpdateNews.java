package com.example.webapptask.controller.impl;

import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.ServiceProvider;
import com.example.webapptask.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UpdateNews implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNEWS_SERVICE();

    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String LOG_ERROR = "unknown command";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String MAIN_PAGE_COMMAND = "controller?command=MAIN_PAGE";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(TITLE_PARAM);
        String content = req.getParameter(CONTENT_PARAM);

        try {
            NEWS_SERVICE.updateNews(title, content);
            resp.sendRedirect(MAIN_PAGE_COMMAND);
        } catch (ServiceException e) {
            log.error(LOG_ERROR, e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
