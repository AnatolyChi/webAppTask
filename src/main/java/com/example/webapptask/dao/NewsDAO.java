package com.example.webapptask.dao;

import com.example.webapptask.bean.News;
import com.example.webapptask.dao.exception.DAOException;

import java.util.List;

public interface NewsDAO {
    void add(int userId, String title, String content) throws DAOException;
    void delete(int newsId) throws DAOException;
    void update(int newsId, String title, String content) throws DAOException;
    List<News> findNews(int currentPage, int recordsPerPage) throws DAOException;
    List<News> searchNews(String tegNews) throws DAOException;
    Integer getQuantityNews() throws DAOException;
    boolean findByTitle(String title) throws DAOException;
    void addToFavorite(String login, String title) throws DAOException;
}
