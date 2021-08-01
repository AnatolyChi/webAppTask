package com.example.webAppTask.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionDAO {
    private static final ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try (InputStream inputStream = new FileInputStream(
                "/Users/tolachikilev/IdeaProjects/webAppTask/src/main/resources/datasource.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            cpds.setDriverClass(properties.getProperty("db.driver"));
            cpds.setJdbcUrl(properties.getProperty("db.url"));
            cpds.setUser(properties.getProperty("db.userName"));
            cpds.setPassword(properties.getProperty("db.password"));

            cpds.setInitialPoolSize(5);
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return cpds;
    }
}
