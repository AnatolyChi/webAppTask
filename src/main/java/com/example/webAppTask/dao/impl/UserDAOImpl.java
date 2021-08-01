package com.example.webAppTask.dao.impl;

import com.example.webAppTask.bean.RegistrationInfo;
import com.example.webAppTask.bean.User;
import com.example.webAppTask.dao.ConnectionDAO;
import com.example.webAppTask.dao.exception.DAOException;
import com.example.webAppTask.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class UserDAOImpl implements UserDAO {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String GET_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String ADD_USER = "INSERT INTO users (login, password) VALUES (?, ?)";

    @Override
    public Optional<User> get(RegistrationInfo info) throws DAOException {
        Optional<User> optionalUser = Optional.empty();

        try (Connection connection = ConnectionDAO.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER)) {

            statement.setString(1, info.getLogin());
            statement.setString(2, info.getPassword());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = Optional.of(new User(
                            resultSet.getString(LOGIN),
                            resultSet.getString(PASSWORD)));
                }
            }
        } catch (SQLException e) {
            log.error("error on get User", e);
            e.printStackTrace();
            throw new DAOException(e);
        }

        return optionalUser;
    }

    @Override
    public void add(RegistrationInfo info) throws DAOException {
        try (Connection connection = ConnectionDAO.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {

            statement.setString(1, info.getLogin());
            statement.setString(2, info.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("error on add User", e);
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {

    }
}
