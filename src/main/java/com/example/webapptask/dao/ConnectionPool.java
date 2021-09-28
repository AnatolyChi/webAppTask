package com.example.webapptask.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.log4j.Log4j2;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Log4j2
public class ConnectionPool {
    private static final ComboPooledDataSource CPDS = new ComboPooledDataSource();

    private static final String BASE_NAME_PATH = "data/datasource";
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER_NAME = "db.userName";
    private static final String PASSWORD = "db.password";
    private static final String LOG_PROPERTY_VETO_EX = "property error";

    private ConnectionPool() { }

    public static Connection getConnection() throws SQLException {
        return CPDS.getConnection();
    }

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME_PATH);
        try {
            CPDS.setDriverClass(resourceBundle.getString(DRIVER));
            CPDS.setJdbcUrl(resourceBundle.getString(URL));
            CPDS.setUser(resourceBundle.getString(USER_NAME));
            CPDS.setPassword(resourceBundle.getString(PASSWORD));

            CPDS.setInitialPoolSize(10);
            CPDS.setMinPoolSize(5);
            CPDS.setAcquireIncrement(10);
            CPDS.setMaxPoolSize(10);
        } catch (PropertyVetoException e) {
            log.error(LOG_PROPERTY_VETO_EX, e);
        }
    }
}
