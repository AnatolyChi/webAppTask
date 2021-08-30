package com.example.webapptask.dao.impl;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.exception.InvalidHashException;
import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.UpdateUserInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.dao.ConnectionPool;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.UserDAO;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.Optional;

@Log4j2
public class UserDAOImpl implements UserDAO {

    private static final String QUERY_FOR_GET = "SELECT * FROM user INNER JOIN role ON user.role_id = role.role_id WHERE login = ?";
    private static final String QUERY_FOR_ADD = "INSERT INTO user (login, password, date_registered) VALUES (?, ?, ?)";
    private static final String QUERY_FOR_UPDATE = "UPDATE user SET firstName = ?, lastName = ?, email = ?, age = ? WHERE user_id = ?";

    private static final String ID_PARAM = "user_id";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FIRSTNAME_PARAM = "firstname";
    private static final String LASTNAME_PARAM = "lastname";
    private static final String EMAIL_PARAM = "email";
    private static final String AGE_PARAM = "age";
    private static final String ROLE_PARAM = "role_name";
    private static final String DATE_REGISTERED_PARAM = "date_registered";

    @Override
    public Optional<User> get(RegistrationInfo info) throws DAOException {
        Optional<User> optionalUser = Optional.empty();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_GET)) {

            statement.setString(1, info.getLogin());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if (Hash.password(info
                            .getPassword()
                            .toCharArray())
                            .verify(resultSet.getString(PASSWORD_PARAM))) {
                        optionalUser = Optional.of(
                                new User.Builder().id(resultSet.getInt(ID_PARAM))
                                        .login(resultSet.getString(LOGIN_PARAM))
                                        .firstname(resultSet.getString(FIRSTNAME_PARAM))
                                        .lastname(resultSet.getString(LASTNAME_PARAM))
                                        .email(resultSet.getString(EMAIL_PARAM))
                                        .age(resultSet.getInt(AGE_PARAM))
                                        .role(resultSet.getString(ROLE_PARAM))
                                        .dateRegistered(resultSet.getDate(DATE_REGISTERED_PARAM))
                                        .build()
                        );
                    }
                }
            }
        } catch (SQLException | InvalidHashException e) {
            log.error("error on get user", e);
            throw new DAOException(e);
        }

        return optionalUser;
    }

    @Override
    public boolean add(RegistrationInfo info) throws DAOException {
        boolean isComplete = false;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_GET)){

            statement.setString(1, info.getLogin());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {

                    try (PreparedStatement statement1 = connection.prepareStatement(QUERY_FOR_ADD)) {
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        statement1.setString(1, info.getLogin());
                        statement1.setString(2, createHash(info.getPassword()));
                        statement1.setTimestamp(3, timestamp);
                        statement1.executeUpdate();
                        isComplete = true;
                    }
                }
            }
        } catch (SQLException e) {
            log.error("error on add user", e);
            throw new DAOException(e);
        }

        return isComplete;
    }

    @Override
    public void update(UpdateUserInfo info) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {

            statement.setString(1, info.getFirstname());
            statement.setString(2, info.getLastname());
            statement.setString(3, info.getEmail());
            statement.setInt(4, info.getAge());
            statement.setInt(5, info.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("error on update user", e);
            throw new DAOException(e);
        }
    }

    private String createHash(String pass) {
        char[] chars = pass.toCharArray();
        return Hash.password(chars).create();
    }
}
