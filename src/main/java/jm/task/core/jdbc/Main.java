package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/preprojectbd";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Olisql123";

    private static final String INSERT_NEW = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM USERS WHERE ID = ?";

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Antony", "Salad", (byte) 88);
        userService.saveUser("Catol", "Twist", (byte) 44);
        userService.saveUser("Dohny", "Qriwler", (byte) 23);
        userService.saveUser("Dash", "Holoren", (byte) 18);

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
