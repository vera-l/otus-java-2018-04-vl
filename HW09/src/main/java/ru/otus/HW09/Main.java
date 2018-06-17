package ru.otus.HW09;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import ru.otus.HW09.exceptions.BadDataModelException;
import ru.otus.HW09.utils.ConnectionUtil;
import ru.otus.HW09.models.UserDataSet;

class Main  {
    public static long NOT_EXIST_ID = 100000000;

    public static void main(String... args) throws ClassNotFoundException, SQLException,
            IOException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException,
            BadDataModelException {

        Executor executor = new Executor(ConnectionUtil.getInstance().getConnection());

        // Add users
        executor.save(new UserDataSet("John", 25));
        executor.save(new UserDataSet("Maria", 31));
        executor.save(new UserDataSet("Peter", 43));

        // Get users
        System.out.println(executor.load(1, UserDataSet.class));
        System.out.println(executor.load(2, UserDataSet.class));
        System.out.println(executor.load(3, UserDataSet.class));
        System.out.println(executor.load(NOT_EXIST_ID, UserDataSet.class));
    }

}