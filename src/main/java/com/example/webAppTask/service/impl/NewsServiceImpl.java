package com.example.webAppTask.service.impl;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.dao.DAOException;
import com.example.webAppTask.dao.DAOProvider;
import com.example.webAppTask.dao.NewsDAO;
import com.example.webAppTask.dao.impl.NewsDAOImpl;
import com.example.webAppTask.service.NewsService;
import com.example.webAppTask.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final NewsDAO newsDAO = PROVIDER.getNewsDAO();

    @Override
    public void add(News news) throws ServiceException {
        try {
            if (!newsDAO.findNews(news)) {
                newsDAO.add(news);
            } else {
                throw new DAOException();
            }
        } catch (DAOException e) {
            // log
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(News news) throws ServiceException {

    }

    @Override
    public List<News> newsList() throws ServiceException {
        return newsDAO.findAll();
    }
}
