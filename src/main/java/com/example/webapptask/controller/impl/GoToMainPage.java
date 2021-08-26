package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class GoToMainPage implements Command {
    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNEWS_SERVICE();

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String NEWS_LIST_PARAM = "newsList";
    private static final String N_OF_PAGES_PARAM = "nOfPages";
    private static final String CURRENT_PAGE_PARAM = "currentPage";
    private static final String TEG_NEWS_PARAM = "tegNews";
    private static final String RECORDS_PER_PAGE_PARAM = "recordsPerPage";
    private static final String LOG_ON_LIST = "error on main page";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cp = req.getParameter(CURRENT_PAGE_PARAM);
        String rpp = req.getParameter(RECORDS_PER_PAGE_PARAM);
        String tegNews = req.getParameter(TEG_NEWS_PARAM);

        if (cp == null || rpp == null) {
            cp = "1";
            rpp = "4";
        }

        int currentPage = Integer.parseInt(cp);
        int recordsPerPage = Integer.parseInt(rpp);

        try {
            List<News> newsList;

            if (tegNews == null || "".equals(tegNews)) {
                newsList = NEWS_SERVICE.newsList(currentPage, recordsPerPage);
            } else {
                newsList = NEWS_SERVICE.searchNews(tegNews);
            }

            int rows = NEWS_SERVICE.getQuantityNews();
            int nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }

            req.setAttribute(NEWS_LIST_PARAM, newsList);
            req.setAttribute(N_OF_PAGES_PARAM, nOfPages);
            req.setAttribute(CURRENT_PAGE_PARAM, currentPage);
            req.setAttribute(RECORDS_PER_PAGE_PARAM, recordsPerPage);
            req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            log.error(LOG_ON_LIST);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
