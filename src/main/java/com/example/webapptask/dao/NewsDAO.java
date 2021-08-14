package com.example.webapptask.dao;

import com.example.webapptask.bean.News;
import com.example.webapptask.dao.exception.DAOException;

import java.util.List;

public interface NewsDAO {
    void add(String title, String content) throws DAOException;
    void delete(String title) throws DAOException;
    List<News> readAllLast() throws DAOException;
    boolean findByTitle(String title) throws DAOException;
}
