package ru.otus.HW09.dao;

import java.sql.SQLException;
import java.util.List;
import ru.otus.HW09.models.DataSet;

public interface DAO<T extends DataSet> {

    T read(long id) throws SQLException;

    List<T> readAll() throws SQLException;

    void save(T dataSet) throws SQLException;

}
