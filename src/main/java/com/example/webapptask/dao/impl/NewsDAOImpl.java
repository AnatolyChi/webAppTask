package com.example.webapptask.dao.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.dao.ConnectionPool;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.NewsDAO;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class NewsDAOImpl implements NewsDAO {

    private static final String QUERY_FOR_ADD = "INSERT INTO news (user_id, title, content, date) VALUES (?, ?, ?, current_timestamp)";
    private static final String QUERY_FOR_UPDATE = "UPDATE news SET title = ?, content = ? WHERE news_id = ?";
    private static final String QUERY_FOR_DELETE = "DELETE FROM news WHERE news_id = ?";
    private static final String QUERY_FOR_SEARCH = "SELECT * FROM news INNER JOIN user USING(user_id) WHERE title LIKE ?";
    private static final String QUERY_FOR_FIND_NEWS = "SELECT * FROM news INNER JOIN user USING(user_id) LIMIT ?, ?";
    private static final String QUERY_FOR_COUNT_ROWS = "SELECT COUNT(news_id) FROM news";
    private static final String QUERY_FOR_FIND_BY_TITLE = "SELECT * FROM news WHERE title = ?";
    private static final String QUERY_FOR_ADD_TO_FAVOURITE = "INSERT INTO favorite_news (user_id, news_id) VALUES (?, ?)";
    private static final String QUERY_FOR_GET_FROM_FAVOURITE = "SELECT * FROM favorite_news WHERE user_id = ? AND news_id = ?";
    private static final String QUERY_FOR_DELETE_FROM_FAVOURITE = "DELETE FROM favorite_news WHERE user_id = ? AND news_id = ?";

    private static final String NEWS_ID_PARAM = "news_id";
    private static final String LOGIN_PARAM = "login";
    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String DATE_PARAM = "date";

    @Override
    public void add(int userId, String title, String content) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_ADD)) {

            statement.setInt(1, userId);
            statement.setString(2, title);
            statement.setString(3, content);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("error on add news", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int newsId) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {

            statement.setInt(1, newsId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("error on delete news", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(int newsId, String title, String content) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {

            statement.setString(1, title);
            statement.setString(2, content);
            statement.setInt(3, newsId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("error on update news", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<News> findNews(int currentPage, int recordsPerPage) throws DAOException {
        List<News> newsList = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_FIND_NEWS)) {

            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    newsList.add(new News(
                            resultSet.getInt(NEWS_ID_PARAM),
                            resultSet.getString(LOGIN_PARAM),
                            resultSet.getString(TITLE_PARAM),
                            resultSet.getString(CONTENT_PARAM),
                            resultSet.getDate(DATE_PARAM)
                    ));
                }
            }
        } catch (SQLException e) {
            log.error("error on find news", e);
            throw new DAOException(e);
        }

        return newsList;
    }

    @Override
    public List<News> searchNews(String tegNews) throws DAOException {
        List<News> newsList = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_SEARCH)) {

            statement.setString(1, "%" + tegNews + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    newsList.add(new News(
                            resultSet.getInt(NEWS_ID_PARAM),
                            resultSet.getString(LOGIN_PARAM),
                            resultSet.getString(TITLE_PARAM),
                            resultSet.getString(CONTENT_PARAM),
                            resultSet.getDate(DATE_PARAM)
                    ));
                }
            }
        } catch (SQLException e) {
            log.error("error on search news", e);
            throw new DAOException(e);
        }

        return newsList;
    }

    @Override
    public Integer getQuantityNews() throws DAOException {
        int numOfRows = 0;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_COUNT_ROWS)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    numOfRows = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error("error on count rows news", e);
            throw new DAOException(e);
        }

        return numOfRows;
    }

    @Override
    public boolean findByTitle(String title) throws DAOException {
        boolean searchResult = false;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_FIND_BY_TITLE)) {

            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next() ) {
                    if (title.equals(resultSet.getString(TITLE_PARAM))) {
                        searchResult = true;
                    }
                }
            }
        } catch (SQLException e) {
            log.error("error on find by title news", e);
            throw new DAOException(e);
        }

        return searchResult;
    }

    @Override
    public boolean addToFavorite(int userId, int newsId) throws DAOException {
        boolean isAdd = false;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_GET_FROM_FAVOURITE)) {

            statement.setInt(1, userId);
            statement.setInt(2, newsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {

                    try (PreparedStatement statement1 = connection.prepareStatement(QUERY_FOR_ADD_TO_FAVOURITE)) {
                        statement1.setInt(1, userId);
                        statement1.setInt(2, newsId);
                        statement1.executeUpdate();
                        isAdd = true;
                    }
                }
            }
        } catch (SQLException e) {
            log.error("error on add to favorite news", e);
            throw new DAOException(e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteFromFavourite(int userId, int newsId) throws DAOException {
        boolean isDelete = false;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_GET_FROM_FAVOURITE)) {

            statement.setInt(1, userId);
            statement.setInt(2, newsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    try (PreparedStatement statement1 = connection.prepareStatement(QUERY_FOR_DELETE_FROM_FAVOURITE)) {
                        statement1.setInt(1, userId);
                        statement1.setInt(2, newsId);
                        statement1.executeUpdate();
                        isDelete = true;
                    }
                }
            }
        } catch (SQLException e) {
            log.error("error on delete from favourite news", e);
            throw new DAOException(e);
        }

        return isDelete;
    }
}
