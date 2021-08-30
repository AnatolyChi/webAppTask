package com.example.webapptask.service;

import com.example.webapptask.bean.News;
import com.example.webapptask.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    boolean addNews(int userId, String title, String content) throws ServiceException;
    void addToFavourite(String login, String title) throws ServiceException;
    void deleteNews(int newsId) throws ServiceException;
    void updateNews(int newsId, String title, String content) throws ServiceException;
    Integer getQuantityNews() throws ServiceException;
    List<News> newsList(int currentPage, int recordsPerPage) throws ServiceException;
    List<News> searchNews(String tegNews) throws ServiceException;
}
