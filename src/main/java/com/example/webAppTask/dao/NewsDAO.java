package com.example.webAppTask.dao;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.dao.exception.DAOException;

import java.util.List;

public interface NewsDAO {
    void add(String title, String content) throws DAOException;
    void delete(String title) throws DAOException;
    List<News> readAllLast() throws DAOException;
    boolean findByTitle(String title) throws DAOException;
}
