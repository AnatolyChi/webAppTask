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
public class AddToFavourite implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNewsService();

    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String USER_ID_PARAM = "user_id";
    private static final String NEWS_ID_PARAM = "news_id";
    private static final String ERROR_ADD_PARAM = "err_add";
    private static final String SUCCESS_ADD_PARAM = "suc_add";
    private static final String GO_TO_READ_NEWS_PAGE = "/WEB-INF/view/news.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter(USER_ID_PARAM));
        int newsId = Integer.parseInt(req.getParameter(NEWS_ID_PARAM));
        String title = req.getParameter(TITLE_PARAM);
        String content = req.getParameter(CONTENT_PARAM);

        try {
            if (NEWS_SERVICE.addToFavourite(userId, newsId)) {
                req.setAttribute(SUCCESS_ADD_PARAM, SUCCESS_ADD_PARAM);
            } else {
                req.setAttribute(ERROR_ADD_PARAM, ERROR_ADD_PARAM);
            }

            req.setAttribute(USER_ID_PARAM, userId);
            req.setAttribute(NEWS_ID_PARAM, newsId);
            req.setAttribute(TITLE_PARAM, title);
            req.setAttribute(CONTENT_PARAM, content);
            req.getRequestDispatcher(GO_TO_READ_NEWS_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            log.error("error add to favorite news");
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
