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
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class AddToFavourite implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNEWS_SERVICE();

    private static final String TITLE = "title";
    private static final String USER_LOGIN = "user_login";
    private static final String ERROR_ADD_PARAM = "err_add";
    private static final String SUCCESS_ADD_PARAM = "suc_add";
    private static final String REFERER = "referer";
    private static final String LOG_ERR_ADD = "error adding";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(TITLE);
        String login = req.getParameter(USER_LOGIN);

        try {
//            if (NEWS_SERVICE.addToFavourite(login, title)) {
//                req.setAttribute(SUCCESS_ADD_PARAM, SUCCESS_ADD_PARAM);
//            } else {
//                req.setAttribute(ERROR_ADD_PARAM, ERROR_ADD_PARAM);
//            }
            NEWS_SERVICE.addToFavourite(login, title);
            req.getRequestDispatcher(req.getHeader(REFERER)).forward(req, resp);
        } catch (ServiceException e) {
            log.error(LOG_ERR_ADD);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
