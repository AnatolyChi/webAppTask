package com.example.webAppTask.dao;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.dao.exception.DAOException;

import java.util.List;

public interface NewsDAO {
    void addNews(String title, String brief) throws DAOException;
    void deleteNews(String title) throws DAOException;
    List<News> newsList() throws DAOException;
    boolean findByTitle(String title) throws DAOException;
}
