package ru.otus.HW10.dao;

import org.hibernate.Session;
import ru.otus.HW09.dao.DAO;
import ru.otus.HW09.models.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO implements DAO<UserDataSet> {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    @Override
    public UserDataSet read(long id) throws SQLException {
        return session.get(UserDataSet.class, id);
    }

    @Override
    public List<UserDataSet> readAll() throws SQLException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list();
    }

    @Override
    public void save(UserDataSet dataSet) throws SQLException {
        session.save(dataSet);
    }
}