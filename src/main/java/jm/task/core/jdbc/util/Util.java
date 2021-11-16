package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String dbDialect = "org.hibernate.dialect.MySQL5Dialect";
    private static final String dbShowSQL = "true";
    private static final String dbCurSesConClass = "thread";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        return connection;
    }

    public static SessionFactory getConnectionHib() throws Exception {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, dbDriver);
        settings.put(Environment.URL, dbUrl);
        settings.put(Environment.USER, dbUsername);
        settings.put(Environment.PASS, dbPassword);
        settings.put(Environment.DIALECT, dbDialect);
        settings.put(Environment.SHOW_SQL, dbShowSQL);
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, dbCurSesConClass);

        configuration.setProperties(settings).addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}
