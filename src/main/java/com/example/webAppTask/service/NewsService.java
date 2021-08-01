package com.example.webAppTask.service;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    void addNews(String title, String brief) throws ServiceException;
    void deleteNews(String title) throws ServiceException;
    List<News> newsList() throws ServiceException;
}
