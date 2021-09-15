package com.example.webapptask.dao;

import com.example.webapptask.dao.exception.DAOException;

public interface MessageDAO {

    void addSuggest(int userId, String title, String content) throws DAOException;
    void addComment(int userId, String content) throws DAOException;
}
