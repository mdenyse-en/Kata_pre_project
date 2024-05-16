package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        service.createUsersTable();
        service.saveUser("Antony", "Salad", (byte) 88);
        service.saveUser("Catol", "Twist", (byte) 44);
        service.saveUser("Dohny", "Qriwler", (byte) 23);
        service.saveUser("Dash", "Holoren", (byte) 18);

        service.removeUserById(1);

        List<User> info = service.getAllUsers();
        for (User us : info) {
            System.out.println(us);
        }

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
