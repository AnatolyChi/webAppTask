package com.example.webAppTask.controller.impl;

import com.example.webAppTask.bean.User;
import com.example.webAppTask.controller.Command;
import com.example.webAppTask.service.NewsService;
import com.example.webAppTask.service.ServiceProvider;
import com.example.webAppTask.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AddNews implements Command {
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNEWS_SERVICE();

    private static final String MAIN_PAGE_COMMAND = "controller?command=MAIN_PAGE";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String NEWS_PAGE = "controller?command=NEWS";
    private static final String CONTENT_PARAM = "content";
    private static final String TITLE_PARAM = "title";
    private static final String LOG_ERROR = "news are null";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(TITLE_PARAM);
        String content = req.getParameter(CONTENT_PARAM);
//        User user = (User) req.getSession().getAttribute("user");

        try {
            if (NEWS_SERVICE.addNews(title, content)) {
                resp.sendRedirect(MAIN_PAGE_COMMAND);
            } else {
                resp.sendRedirect(NEWS_PAGE);
            }
        } catch (ServiceException e) {
            log.error(LOG_ERROR, e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
