package com.example.webapptask.controller.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.controller.Command;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.exception.ServiceException;
import com.example.webapptask.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
public class GoToMainPage implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNewsService();

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String NEWS_LIST_PARAM = "newsList";
    private static final String TEG_NEWS_PARAM = "tegNews";
    private static final String N_OF_PAGES_PARAM = "nOfPages";
    private static final String CURRENT_PAGE_PARAM = "currentPage";
    private static final String RECORDS_PER_PAGE_PARAM = "recordsPerPage";
    private static final String CP_DEFAULT = "1";
    private static final String RPP_DEFAULT = "4";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cp = req.getParameter(CURRENT_PAGE_PARAM);
        String rpp = req.getParameter(RECORDS_PER_PAGE_PARAM);
        String tegNews = req.getParameter(TEG_NEWS_PARAM);

        if (cp == null || rpp == null) {
            cp = CP_DEFAULT;
            rpp = RPP_DEFAULT;
        }

        int currentPage = Integer.parseInt(cp);
        int recordsPerPage = Integer.parseInt(rpp);

        try {
            List<News> newsList;

            if (tegNews == null || tegNews.isEmpty()) {
                newsList = NEWS_SERVICE.newsList(currentPage, recordsPerPage);
            } else {
                newsList = NEWS_SERVICE.searchNewsByTags(tegNews);
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
            log.error("error on main page", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }
}
