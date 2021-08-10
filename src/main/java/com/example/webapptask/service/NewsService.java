package com.example.webapptask.service;

import com.example.webapptask.bean.News;
import com.example.webapptask.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    boolean addNews(String title, String content, String author) throws ServiceException;
    void deleteNews(String title) throws ServiceException;
    List<News> newsList() throws ServiceException;
}
