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

    private static final String LOG_ERROR_VALIDATE = "validate err";
    private static final String LOG_ERROR_ON_ADD = "news not was added";
    private static final String LOG_ERROR_ON_DELETE = "news not was deleted";
    private static final String LOG_ERROR_ON_READ_ALL = "news not was read";
    private static final String LOG_ERROR_ON_UPDATE = "new not was updated";

    @Override
    public boolean addNews(String title, String content, String userLogin) throws ServiceException {
        if (validate(title, content, userLogin)) {
            log.error(LOG_ERROR_VALIDATE);
            throw new ServiceException();
        }
        boolean statusAdded = false;

        try {
            if (!NEWS_DAO.findByTitle(title)) {
                NEWS_DAO.add(title, content, userLogin);
                statusAdded = true;
            }
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_ADD);
            throw new ServiceException(e);
        }

        return statusAdded;
    }

    @Override
    public void deleteNews(String title) throws ServiceException {
        if (validate(title)) {
            log.error(LOG_ERROR_VALIDATE);
            throw new ServiceException();
        }

        try {
            NEWS_DAO.delete(title);
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_DELETE);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNews(String title, String content, String currentTitle) throws ServiceException {
        if (validate(title, content, currentTitle)) {
            log.error(LOG_ERROR_VALIDATE);
            throw new ServiceException();
        }

        try {
            NEWS_DAO.update(title, content, currentTitle);
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_UPDATE);
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getQuantityNews() throws ServiceException {
        try {
            return NEWS_DAO.getQuantityNews();
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_READ_ALL);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> newsList(int currentPage, int recordsPerPage) throws ServiceException {
        try {
            return NEWS_DAO.findNews(currentPage, recordsPerPage);
        } catch (DAOException e) {
            log.info(LOG_ERROR_ON_READ_ALL);
            throw new ServiceException(e);
        }
    }

    private boolean validate(String ... values) {
        boolean isVal = false;

        for (String value : values) {
            if (value == null || value.isEmpty()) {
                isVal = true;
                break;
            }
        }

        return isVal;
    }
}
