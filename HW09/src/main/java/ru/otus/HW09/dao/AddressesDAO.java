package ru.otus.HW09.dao;

import ru.otus.HW09.Executor;
import ru.otus.HW09.models.AddressDataSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressesDAO implements DAO<AddressDataSet> {

    private Executor executor;

    public AddressesDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public AddressDataSet read(long id) throws SQLException {
        return executor.load(id, AddressDataSet.class);
    }

    @Override
    public List<AddressDataSet> readAll() throws SQLException {
        return executor.load(AddressDataSet.class);
    }

    @Override
    public void save(AddressDataSet dataSet) throws SQLException {
        executor.save(dataSet);
    }
}