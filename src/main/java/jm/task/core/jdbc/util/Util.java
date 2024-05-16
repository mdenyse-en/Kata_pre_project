package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/preprojectbd";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Olisql123";

    private final Connection connection;
    private final SessionFactory factory;

    public Util() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Configuration config = getConfiguration();

        config.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());

        factory = config.buildSessionFactory(sBuilder.build());
    }

    private Configuration getConfiguration() {
        Configuration config = new Configuration();

        config.setProperty(Environment.URL, URL);
        config.setProperty(Environment.USER, USERNAME);
        config.setProperty(Environment.PASS, PASSWORD);
        config.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        config.setProperty(Environment.DEFAULT_SCHEMA, "preprojectbd");
        return config;
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public SessionFactory getFactory() throws SQLException {
        return factory;
    }
}
