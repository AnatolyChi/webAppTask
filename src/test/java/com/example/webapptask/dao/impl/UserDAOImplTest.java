package com.example.webapptask.dao.impl;

import com.example.webapptask.bean.RegistrationInfo;
import com.example.webapptask.dao.exception.DAOException;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
class UserDAOImplTest {

    private static DataSource dataSource = Mockito.mock(DataSource.class);
    private static Connection connection = Mockito.mock(Connection.class);
    private static PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static RegistrationInfo info = Mockito.mock(RegistrationInfo.class);

    private String LOGIN = "Admin-4";
    private String PASSWORD = "123Admin";

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
    }

    @Test
    void testAddUser() throws SQLException, DAOException {
        Mockito.when(statement.executeUpdate("INSERT INTO user (login, password, date_registered) VALUES (?, ?, ?)")).thenReturn(1);
        Mockito.when(info.getLogin()).thenReturn(LOGIN);
        Mockito.when(info.getPassword()).thenReturn(PASSWORD);

        Assertions.assertTrue(new UserDAOImpl().add(info));
    }

    @AfterEach
    void erasure() throws SQLException {
        // Удалять моком из базы проверяемые значения
        // Реализовать
        LOGIN = null;
        PASSWORD = null;
        connection.close();
    }
}