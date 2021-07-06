package com.example.webAppTask.service;

import com.example.webAppTask.bean.News;

import java.util.List;

public interface NewsService {
    void add(News news) throws ServiceException;
    void update(News news) throws ServiceException;
    List<News> newsList() throws ServiceException;
}
