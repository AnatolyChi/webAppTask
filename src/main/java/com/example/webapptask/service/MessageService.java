package com.example.webapptask.service;

import com.example.webapptask.dao.exception.DAOException;

public interface MessageService {

    void suggestNews(int userId, String title, String content) throws DAOException;
    void commentNews(int userId, String content) throws DAOException;
}
