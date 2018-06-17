package ru.otus.HW09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.HW09.exceptions.BadDataModelException;
import ru.otus.HW09.models.UserDataSet;
import ru.otus.HW09.utils.ConnectionUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class ExecutorTests {

    private static Connection connection;
    private Executor executor;

    @BeforeClass
    public static void setup() throws ClassNotFoundException, IOException, SQLException {
        connection = ConnectionUtil.getInstance().getConnection();
    }

    @Before
    public void methodSetup() {
        executor = new Executor(connection);
    }

    @Test
    public void usersShouldBeAdded() throws BadDataModelException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, SQLException,
            InstantiationException {

        UserDataSet user1 = new UserDataSet("Mike", 27);
        executor.save(user1);

        UserDataSet user2 = executor.load(4, UserDataSet.class);

        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertEquals(user1.getAge(), user2.getAge());
    }

}