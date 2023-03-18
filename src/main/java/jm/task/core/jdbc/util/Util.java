package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {


    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javastudy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Connection complete");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    private static SessionFactory sessionFactory;
    public static SessionFactory openSession() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration();

                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/javastudy");
                properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                properties.setProperty("show_sql", "true");
                properties.setProperty("hibernate.connection.username", "root");
                properties.setProperty("hibernate.connection.password", "root");
                properties.setProperty("hibernate.hbm2ddl.auto", "");

                cfg.setProperties(properties);
                cfg.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties())
                        .build();

                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void closeSession () {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    // реализуйте настройку соеденения с БД
}
