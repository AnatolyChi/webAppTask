package com.example.webAppTask.service.impl;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.dao.exception.DAOException;
import com.example.webAppTask.dao.DAOProvider;
import com.example.webAppTask.dao.NewsDAO;
import com.example.webAppTask.service.NewsService;
import com.example.webAppTask.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NewsServiceImpl implements NewsService {
    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final NewsDAO NEWS_DAO = PROVIDER.getNewsDAO();

    @Override
    public void addNews(String title, String content) throws ServiceException {
        try {
            NEWS_DAO.addNews(title, content);
        } catch (DAOException e) {
            log.info("news are not added");
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteNews(String title) throws ServiceException {
        try {
            NEWS_DAO.deleteNews(title);
        } catch (DAOException e) {
            log.info("news are not deleted");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> newsList() throws ServiceException {
        try {
            return NEWS_DAO.newsList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
