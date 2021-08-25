package com.example.webapptask.service;

import com.example.webapptask.bean.News;
import com.example.webapptask.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    boolean addNews(String title, String content, String userLogin) throws ServiceException;
    void deleteNews(String title) throws ServiceException;
    void updateNews(String title, String content, String currentTitle) throws ServiceException;
    Integer getQuantityNews() throws ServiceException;
    List<News> newsList(int currentPage, int recordsPerPage) throws ServiceException;
}
