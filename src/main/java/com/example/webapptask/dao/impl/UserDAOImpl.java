package com.example.webapptask.dao.impl;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.exception.InvalidHashException;
import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.bean.User;
import com.example.webapptask.dao.ConnectionPool;
import com.example.webapptask.dao.exception.DAOException;
import com.example.webapptask.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class UserDAOImpl implements UserDAO {

    private static final String QUERY_FOR_GET = "SELECT * FROM user INNER JOIN role ON user.role_id = role.role_id WHERE login = ?";
    private static final String QUERY_FOR_ADD = "INSERT INTO user (login, password, dateRegistered) VALUES (?, ?, ?)";
    private static final String QUERY_FOR_UPDATE = "UPDATE user SET firstName = ?, lastName = ?, email = ? WHERE login = ?";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role_name";
    private static final String DATE_REGISTERED = "dateRegistered";
    private static final String ERROR_ON_GET = "error on get User";
    private static final String ERROR_ON_ADD = "error on add User";
    private static final String ERROR_ON_UPDATE = "error on update User";

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
                            .verify(resultSet.getString(PASSWORD))) {
                        optionalUser = Optional.of(new User(
                                resultSet.getString(LOGIN),
                                resultSet.getString(ROLE),
                                resultSet.getDate(DATE_REGISTERED)));
                    }
                }
            }
        } catch (SQLException | InvalidHashException e) {
            log.error(ERROR_ON_GET, e);
            throw new DAOException(e);
        }

        return optionalUser;
    }

    @Override
    public Optional<User> add(RegistrationInfo info) throws DAOException {
        Optional<User> optionalUser = Optional.empty();

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
                        optionalUser = Optional.of(new User(info.getLogin(), timestamp));
                    }
                }
            }
        } catch (SQLException e) {
            log.error(ERROR_ON_ADD, e);
            throw new DAOException(e);
        }

        return optionalUser;
    }

    @Override
    public void update(User user, String firstName, String lastName, String email, String age) throws DAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, user.getLogin());
            statement.executeUpdate();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAge(age);
        } catch (SQLException e) {
            log.error(ERROR_ON_UPDATE);
            throw new DAOException(e);
        }
    }

    private String createHash(String pass) {
        char[] chars = pass.toCharArray();
        return Hash.password(chars).create();
    }
}
