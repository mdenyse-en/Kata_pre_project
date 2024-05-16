package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

            session.createSQLQuery(querySQL).addEntity(User.class).executeUpdate();
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

            session.createSQLQuery(querySQL).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while DROP table!");
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getFactory().openSession()) {
            session.save(new User(name, lastName, age));
        } catch (SQLException ex) {
            System.out.println("Exception while SAVE USER!");
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while REMOVE USER by ID!");
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String queryHQL = "FROM User";
        List<User> result = new ArrayList<>();

        try (Session session = util.getFactory().openSession()) {
            result = session.createQuery(queryHQL, User.class).list();
        } catch (SQLException ex) {
            System.out.println("Exception while GET ALL USERS!");
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        String queryHQL = "DELETE FROM User u";

        try (Session session = util.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createQuery(queryHQL).executeUpdate();
            transaction.commit();
        } catch (SQLException ex) {
            System.out.println("Exception while CLEAN table!");
            ex.printStackTrace();
        }
    }
}
