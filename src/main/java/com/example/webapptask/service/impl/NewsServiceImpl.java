package com.example.webapptask.service.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.DAOProvider;
import com.example.webapptask.dao.NewsDAO;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NewsServiceImpl implements NewsService {
    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final NewsDAO NEWS_DAO = PROVIDER.getNEWS_DAO();

    private static final String ERR_MESSAGE = "Incorrect variables";
    private static final String LOG_ERROR_ON_ADD = "news not was added";
    private static final String LOG_ERROR_ON_DELETE = "news not was deleted";
    private static final String LOG_ERROR_ON_READ_ALL = "news not was read";

    @Override
    public boolean addNews(String title, String content, String author) throws ServiceException {
        validate(title, content, author);
        boolean statusAdded = false;

        try {
            if (!NEWS_DAO.findByTitle(title)) {
                NEWS_DAO.add(title, content, author);
                statusAdded = true;
            }
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_ADD);
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return statusAdded;
    }

    @Override
    public void deleteNews(String title) throws ServiceException {
        validate(title);

        try {
            NEWS_DAO.delete(title);
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_DELETE);
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> newsList() throws ServiceException {
        try {
            return NEWS_DAO.readAllLast();
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_READ_ALL);
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    private void validate(String ... values) throws ServiceException {
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                throw new ServiceException(ERR_MESSAGE);
            }
        }
    }
}
