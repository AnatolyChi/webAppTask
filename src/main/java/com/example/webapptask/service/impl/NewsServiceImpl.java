package com.example.webapptask.service.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.DAOProvider;
import com.example.webapptask.dao.NewsDAO;
import com.example.webapptask.service.NewsService;
import com.example.webapptask.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class NewsServiceImpl implements NewsService {

    private static final DAOProvider PROVIDER = DAOProvider.getInstance();
    private static final NewsDAO NEWS_DAO = PROVIDER.getNEWS_DAO();

    @Override
    public boolean addNews(int userId, String title, String content) throws ServiceException {
        boolean statusAdded = false;

        try {
            if (!NEWS_DAO.findByTitle(title)) {
                NEWS_DAO.add(userId, title, content);
                statusAdded = true;
            }
        } catch (DAOException e) {
            log.error("news not was added", e);
            throw new ServiceException(e);
        }

        return statusAdded;
    }

    @Override
    public boolean addToFavourite(int userId, int newsId) throws ServiceException {

        try {
            return NEWS_DAO.addToFavorite(userId, newsId);
        } catch (DAOException e) {
            log.error("news was not added to favourite", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteFromFavourite(int userId, int newsId) throws ServiceException {

        try {
            return NEWS_DAO.deleteFromFavourite(userId, newsId);
        } catch (DAOException e) {
            log.error("news was not deleting from favourite", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteNews(int newsId) throws ServiceException {

        try {
            NEWS_DAO.delete(newsId);
        } catch (DAOException e) {
            log.error("news not was deleted", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNews(int newsId, String title, String content) throws ServiceException {

        try {
            NEWS_DAO.update(newsId, title, content);
        } catch (DAOException e) {
            log.error("news not was updated", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getQuantityNews() throws ServiceException {

        try {
            return NEWS_DAO.getQuantityNews();
        } catch (DAOException e) {
            log.error("news not was counted", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> newsList(int currentPage, int recordsPerPage) throws ServiceException {

        try {
            return NEWS_DAO.findNews(currentPage, recordsPerPage);
        } catch (DAOException e) {
            log.error("error on create news list", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> favouriteNewsList(int userId) throws ServiceException {

        try {
            return NEWS_DAO.findFavouriteNews(userId);
        } catch (DAOException e) {
            log.error("error on favourite news list", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> searchNewsByTags(String tegNews) throws ServiceException {

        try {
            return NEWS_DAO.searchNewsByTags(tegNews);
        } catch (DAOException e) {
            log.error("error on search news", e);
            throw new ServiceException(e);
        }
    }
}
