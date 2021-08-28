package com.example.webapptask.dao.impl;

import com.example.webapptask.bean.News;
import com.example.webapptask.dao.ConnectionPool;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.NewsDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NewsDAOImpl implements NewsDAO {

    private static final String QUERY_FOR_ADD_TO_FAVOURITE =
            "INSERT INTO favorite_news (user_id, news_id) VALUES ((SELECT user_id FROM user WHERE login = ?), (SELECT news_id FROM news WHERE title = ?))";
    private static final String QUERY_FOR_ADD =
            "INSERT INTO news (title, content, user_id, date) VALUES (?, ?, (SELECT user_id FROM user WHERE login = ?), current_timestamp)";
    private static final String QUERY_FOR_SEARCH = "SELECT * FROM news INNER JOIN user USING(user_id) WHERE title LIKE ?";
    private static final String QUERY_FOR_FIND_NEWS = "SELECT * FROM news INNER JOIN user USING(user_id) LIMIT ?, ?";
    private static final String QUERY_FOR_UPDATE = "UPDATE news SET title = ?, content = ? WHERE title = ?";
    private static final String QUERY_FOR_FIND_BY_TITLE = "SELECT * FROM news WHERE title = ?";
    private static final String QUERY_FOR_COUNT_ROWS = "SELECT COUNT(news_id) FROM news";
    private static final String QUERY_FOR_DELETE = "DELETE FROM news WHERE title = ?";

    private static final String LOG_ON_ADD = "error on add News";
    private static final String LOG_ON_DELETE = "error on delete by title";
    private static final String LOG_ON_UPDATE = "error on update by title";
    private static final String LOG_ON_FIND_NEWS = "error on find news";
    private static final String LOG_ON_COUNT_ROWS = "error on count rows";
    private static final String LOG_ON_SEARCH_NEWS = "error on search news";
    private static final String LOG_ON_FIND_BY_TITLE = "error on find news";
    private static final String LOG_ON_ADD_TO_FAVOURITE = "error on add favourite";
    private static final String TITLE_PARAM = "title";
    private static final String CONTENT_PARAM = "content";
    private static final String LOGIN_PARAM = "login";
    private static final String DATE_PARAM = "date";

    @Override
    public void add(String title, String content, String userLogin) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_ADD)) {

            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, userLogin);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LOG_ON_ADD, e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(String title) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {

            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LOG_ON_DELETE, e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(String title, String content, String currentTitle) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {

            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, currentTitle);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(LOG_ON_UPDATE);
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
                            resultSet.getString(TITLE_PARAM),
                            resultSet.getString(CONTENT_PARAM),
                            resultSet.getString(LOGIN_PARAM),
                            resultSet.getDate(DATE_PARAM)
                    ));
                }
            }
        } catch (SQLException e) {
            log.error(LOG_ON_FIND_NEWS);
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
                            resultSet.getString(TITLE_PARAM),
                            resultSet.getString(CONTENT_PARAM),
                            resultSet.getString(LOGIN_PARAM),
                            resultSet.getDate(DATE_PARAM)
                    ));
                }
            }
        } catch (SQLException e) {
            log.error(LOG_ON_SEARCH_NEWS);
            e.printStackTrace();
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
            log.error(LOG_ON_COUNT_ROWS);
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
            log.error(LOG_ON_FIND_BY_TITLE, e);
            throw new DAOException(e);
        }

        return searchResult;
    }

    @Override
    public void addToFavorite(String login, String title) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_ADD_TO_FAVOURITE)) {

            statement.setString(1, login);
            statement.setString(2, title);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error(QUERY_FOR_ADD_TO_FAVOURITE);
            throw new DAOException(e);
        }
    }
}
