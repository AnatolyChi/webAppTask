package com.example.webAppTask.dao.impl;

import com.example.webAppTask.bean.News;
import com.example.webAppTask.dao.ConnectionPool;
import com.example.webAppTask.dao.exception.DAOException;
import com.example.webAppTask.dao.NewsDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class NewsDAOImpl implements NewsDAO {

    @Override
    public void addNews(String title, String brief) throws DAOException {
        String sql = "INSERT INTO news (title, content) VALUES (?, ?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, brief);
            statement.execute();
        } catch (SQLException e) {
            log.error("error on add News", e);
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public void deleteNews(String title) throws DAOException {
        String sql = "DELETE FROM news WHERE title = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.execute();
        } catch (SQLException e) {
            log.error("error on deleting title", e);
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<News> newsList() throws DAOException {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    newsList.add(new News(
                            resultSet.getString("title"),
                            resultSet.getString("content")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }

        return newsList;
    }

    @Override
    public Optional<News> get(String title) throws DAOException {
        return Optional.empty();
        // Пока так, без реализации
    }
}
