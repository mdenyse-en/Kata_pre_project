package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/preprojectbd";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Olisql123";

    private final Connection connection;

    public Util() throws SQLException{
        Driver driver = new com.mysql.cj.jdbc.Driver();

        DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }
}
