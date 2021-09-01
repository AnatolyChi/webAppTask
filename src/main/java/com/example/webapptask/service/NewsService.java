package com.example.webapptask.service;

import com.example.webapptask.bean.News;
import com.example.webapptask.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    boolean addNews(int userId, String title, String content) throws ServiceException;
    boolean addToFavourite(int userId, int newsId) throws ServiceException;
    boolean deleteFromFavourite(int userId, int newsId) throws ServiceException;
    void deleteNews(int newsId) throws ServiceException;
    void updateNews(int newsId, String title, String content) throws ServiceException;
    Integer getQuantityNews() throws ServiceException;
    List<News> searchNewsByTags(String tegNews) throws ServiceException;
    List<News> newsList(int currentPage, int recordsPerPage) throws ServiceException;
    List<News> favouriteNewsList(int userId) throws ServiceException;
}
