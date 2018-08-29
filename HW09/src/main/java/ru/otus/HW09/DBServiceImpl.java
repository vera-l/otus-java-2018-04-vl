package ru.otus.HW09;

import ru.otus.HW09.dao.UsersDAO;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW09.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DBServiceImpl implements DBService {

    private UsersDAO usersDAO;

    public DBServiceImpl() {
        try {
            this.usersDAO = new UsersDAO(
                ConnectionUtil.getInstance().getConnection()
            );
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public UserDataSet read(long userId) {
        try {
            return usersDAO.read(userId);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    @Override
    public List<UserDataSet> readAll() {
        try {
            return usersDAO.readAll();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    @Override
    public void save(UserDataSet user) {
        try {
            usersDAO.save(user);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
