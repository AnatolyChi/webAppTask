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
public class AddNews implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNewsService();

    private static final String MAIN_PAGE = "controller?command=MAIN_PAGE";
    private static final String GO_TO_ADD_NEWS = "/WEB-INF/view/add_news.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String USER_ID_PARAM = "user_id";
    private static final String MESSAGE_ERROR_PARAM = "message_err";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(TITLE_PARAM);
        String content = req.getParameter(CONTENT_PARAM);
        int userId = Integer.parseInt(req.getParameter(USER_ID_PARAM));

        if (littleValidation(title, content)) {
            req.getRequestDispatcher(GO_TO_ADD_NEWS).forward(req, resp);
            return;
        }

        try {
            if (NEWS_SERVICE.addNews(userId, title, content)) {
                resp.sendRedirect(MAIN_PAGE);
            } else {
                req.setAttribute(MESSAGE_ERROR_PARAM, MESSAGE_ERROR_PARAM);
                req.getRequestDispatcher(GO_TO_ADD_NEWS).forward(req, resp);
            }
        } catch (ServiceException e) {
            log.error("news are not added", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }

    private boolean littleValidation(String title, String content) {
        return title == null || title.isEmpty() ||
                content == null || content.isEmpty();
    }
}
