package com.hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Change these constants according to your local DB
    private static final String URL = "jdbc:mysql://localhost:3306/hmsdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "password";

    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database. Update Database.java with correct credentials.");
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
