package com.hznu.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final ThreadLocal<Connection> dbConnectionLocal = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("", "", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    });

    public Connection getConnection() {
        return dbConnectionLocal.get();
    }
}
