package ru.otus.HW10;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.otus.HW09.DBService;
import ru.otus.HW09.models.AddressDataSet;
import ru.otus.HW09.models.PhoneDataSet;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW10.dao.UsersDAO;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

class DBServiceHibernateImpl implements DBService {

    private SessionFactory sessionFactory;

    DBServiceHibernateImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        try {
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("!!! Initial SessionFactory creation failed: " + e);
        }
    }

    @Override
    public UserDataSet read(long userId) {
        UserDataSet result = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                result = new UsersDAO(session).read(userId);
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
            transaction.commit();
        }

        return result;
    }

    @Override
    public List<UserDataSet> readAll() {
        List<UserDataSet> result = Collections.emptyList();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                result = new UsersDAO(session).readAll();
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
            transaction.commit();
        }

        return result;
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                new UsersDAO(session).save(user);
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
            transaction.commit();
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
