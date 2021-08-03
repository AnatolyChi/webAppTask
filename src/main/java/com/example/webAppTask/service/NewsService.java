package com.example.webAppTask.service;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    boolean addNews(String title, String content, String username) throws ServiceException;
    void deleteNews(String title) throws ServiceException;
    List<News> newsList() throws ServiceException;
}
