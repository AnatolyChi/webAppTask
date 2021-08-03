package com.example.webAppTask.dao.impl;

import com.example.webAppTask.bean.RegistrationInfo;
import com.example.webAppTask.bean.User;
import com.example.webAppTask.dao.ConnectionPool;
import com.example.webAppTask.dao.exception.DAOException;
import com.example.webAppTask.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class UserDAOImpl implements UserDAO {

    private static final String QUERY_FOR_ADD = "INSERT INTO users (login, password, dateRegistered) VALUES (?, ?, current_timestamp)";
    private static final String QUERY_FOR_GET = "SELECT * FROM users WHERE login = ? AND password = ?";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String DATE_REG = "dateRegistered";
    private static final String ERROR_ON_GET = "error on get User";
    private static final String ERROR_ON_ADD = "error on add User";

    @Override
    public Optional<User> get(RegistrationInfo info) throws DAOException {
        Optional<User> optionalUser = Optional.empty();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_GET)) {

            statement.setString(1, info.getLogin());
            statement.setString(2, info.getPassword());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = Optional.of(new User(
                            resultSet.getString(LOGIN),
                            resultSet.getString(PASSWORD),
                            resultSet.getDate(DATE_REG)));
                }
            }
        } catch (SQLException e) {
            log.error(ERROR_ON_GET, e);
            e.printStackTrace();
            throw new DAOException(e);
        }

        return optionalUser;
    }

    @Override
    public void add(RegistrationInfo info) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_ADD)) {

            statement.setString(1, info.getLogin());
            statement.setString(2, info.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(ERROR_ON_ADD, e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        // заглушка
    }
}
