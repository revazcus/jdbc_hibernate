package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getConnectionHib().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS mydbtest.users (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR (45) NOT NULL, lastName VARCHAR (45) NOT NULL, age INT NOT NULL)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getConnectionHib().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS mydbtest.users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getConnectionHib().openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getConnectionHib().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getConnectionHib().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User");
            users = query.list();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getConnectionHib().openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
