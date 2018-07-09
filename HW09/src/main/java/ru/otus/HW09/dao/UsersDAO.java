package ru.otus.HW09.dao;

import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW09.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO implements DAO<UserDataSet> {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public UserDataSet read(long id) throws SQLException {
        return executor.load(id, UserDataSet.class);
    }

    @Override
    public List<UserDataSet> readAll() throws SQLException {
        return executor.load(UserDataSet.class);
    }

    @Override
    public void save(UserDataSet dataSet) throws SQLException {
        executor.save(dataSet);
    }
}