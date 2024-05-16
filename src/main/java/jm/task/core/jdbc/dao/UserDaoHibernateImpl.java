package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util;

    public UserDaoHibernateImpl() {
        try {
            util = new Util();
        } catch (SQLException ex) {
            System.out.println("Bad connection to DB");
            ex.printStackTrace();
        }
    }

    @Override
    public void createUsersTable() {
        String querySQL = "CREATE TABLE IF NOT EXISTS users(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(20) NOT NULL," +
                "lastname VARCHAR(20) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY(id));";

        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(querySQL).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while CREATE table USERS!");
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String querySQL = "DROP TABLE IF EXISTS users";

        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(querySQL).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while DROP table!");
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String querySQL = "INSERT INTO users(name, lastname, age) VALUES('" + name + "', '" + lastName + "', '" + age + "');";

        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(querySQL).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while SAVE USER!");
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String querySQL = "DELETE FROM users WHERE id = " + id;

        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(querySQL).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while REMOVE USER by ID!");
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String querySQL = "select * FROM users";
        List<User> result = new ArrayList<>();

        try (Session session = util.getFactory().openSession()) {
            result = session.createSQLQuery(querySQL).addEntity(User.class).list();
        } catch (SQLException ex) {
            System.out.println("Exception while GET ALL USERS!");
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        String querySQL = "DELETE FROM users";

        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(querySQL).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while CLEAN table!");
            ex.printStackTrace();
        }
    }
}
