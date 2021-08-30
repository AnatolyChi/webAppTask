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
public class UpdateNews implements Command {

    private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
    private static final NewsService NEWS_SERVICE = PROVIDER.getNewsService();

    private static final String NEWS_ID_PARAM = "news_id";
    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String UNKNOWN_COMMAND = "controller?command=UNKNOWN_COMMAND";
    private static final String MAIN_PAGE_COMMAND = "controller?command=MAIN_PAGE";
    private static final String GO_TO_UPDATE_NEWS_PAGE = "/WEB-INF/view/update_news.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(NEWS_ID_PARAM);
        String title = req.getParameter(TITLE_PARAM);
        String content = req.getParameter(CONTENT_PARAM);

        if (littleValidation(title, content)) {
            req.setAttribute(NEWS_ID_PARAM, id);
            req.setAttribute(TITLE_PARAM, title);
            req.setAttribute(CONTENT_PARAM, content);
            req.getRequestDispatcher(GO_TO_UPDATE_NEWS_PAGE).forward(req, resp);
            return;
        }

        try {
            System.err.println(id);
            int newsId = Integer.parseInt(id);
            NEWS_SERVICE.updateNews(newsId, title, content);
            resp.sendRedirect(MAIN_PAGE_COMMAND);
        } catch (ServiceException e) {
            log.error("error on update news", e);
            resp.sendRedirect(UNKNOWN_COMMAND);
        }
    }

    private boolean littleValidation(String title, String content) {
        return title == null || title.isEmpty() ||
                content == null || content.isEmpty();
    }
}
