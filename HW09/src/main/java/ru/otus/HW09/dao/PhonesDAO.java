package ru.otus.HW09.dao;

import ru.otus.HW09.Executor;
import ru.otus.HW09.models.PhoneDataSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PhonesDAO implements DAO<PhoneDataSet> {

    private Executor executor;

    public PhonesDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public PhoneDataSet read(long id) throws SQLException {
        return executor.load(id, PhoneDataSet.class);
    }

    @Override
    public List<PhoneDataSet> readAll() throws SQLException {
        return executor.load(PhoneDataSet.class);
    }

    @Override
    public void save(PhoneDataSet dataSet) throws SQLException {
        executor.save(dataSet);
    }
}