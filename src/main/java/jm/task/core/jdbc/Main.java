package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
//import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/preprojectbd";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Olisql123";

    private static final String INSERT_NEW = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM USERS WHERE ID = ?";

    public static void main(String[] args) {
//        Connection connection;

//        try {
//            Driver driver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.registerDriver(driver);
//
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            if (!connection.isClosed()) {
//                System.out.println("Connection open!");
//            }
//            connection.close();
//
//            if (connection.isClosed()) {
//                System.out.println("Connection close!");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Driver error!" + ex.toString());
//        }
/*
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            System.out.println("Driver error!" + ex.toString());
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
//            statement.execute("INSERT INTO users(name, lastname, age) VALUES('Kola', 'Ivanov', 15);");
//            statement.execute("SELECT * from users");
//            int id = statement.executeUpdate("UPDATE users SET name = 'Nikola' WHERE name = 'Kola'; ");
//            ResultSet res = statement.executeQuery("Select * from users;");

//            statement.addBatch("INSERT INTO users(name, lastname, age) VALUES('Kola', 'Ivanov', 15);");
//            statement.addBatch("INSERT INTO users(name, lastname, age) VALUES('Kola', 'Sidorov', 21);");
//            statement.addBatch("INSERT INTO users(name, lastname, age) VALUES('Kola', 'Petrov', 33);");

            statement.executeBatch();
            statement.clearBatch();

            System.out.println(statement.isClosed());

//            statement.close();

            String query = "select * from users";




            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1, 14);
            preparedStatement.setString(2, "VOva");
            preparedStatement.setString(3, "Zubarefff");
            preparedStatement.setInt(4, 36);
            preparedStatement.execute();



            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, 14);
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString(3);
                int age = result.getInt(4);
                System.out.println(id + " " + name + " " + lastname + " " + age);
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        */

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Antony", "Salad", (byte) 88);
        userService.removeUserById(12);
        userService.getAllUsers();
        userService.dropUsersTable();
    }
}
