package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;

    public UserDaoJDBCImpl() {
        try {
            util = new Util();
        } catch (SQLException e) {
            System.out.println("Bad connection to DB");
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(20) NOT NULL," +
                "lastname VARCHAR(20) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY(id));";

        try (Statement statement = util.getStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Exception while CREATE table USERS!");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";

        try (Statement statement = util.getStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Exception while DROP table USERS!");
            e.printStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users(name, lastname, age) VALUES('" + name + "', '" + lastName + "', '" + age + "');";

        try (Statement statement = util.getStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Exception while save USER!");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = " + id;

        try (Statement statement = util.getStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Exception while remove USER by ID!");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String query = "select * from users";

        try (Statement statement = util.getStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User tmp = new User();

                tmp.setId(resultSet.getLong(1));
                tmp.setName(resultSet.getString(2));
                tmp.setLastName(resultSet.getString(3));
                tmp.setAge(resultSet.getByte(4));

                result.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println("Exception while CLEAN table!");
            e.printStackTrace();
        }

        return result;
    }

    public void cleanUsersTable() {
        String query = "delete from users";

        try (Statement statement = util.getStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Exception while CLEAN table!");
            e.printStackTrace();
        }
    }
}
