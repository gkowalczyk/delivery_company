package com.example.infrastructures.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DbManager {
    INSTANCE;
    private Connection connection;

    DbManager() {
        Properties properties = new Properties();
        properties.put("user", "sa");
        properties.put("password", "");

        try {
            connection = DriverManager.getConnection(
                    "jdbc:h2:mem:testdb", properties);

        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DbManager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
