package ru.otus.HW09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.HW09.models.UserDataSet;


public class ExecutorTests {

    private DBService dbService;

    @Before
    public void methodSetup() {
        dbService = new DBServiceImpl();
    }

    @Test
    public void usersShouldBeAdded() {
        UserDataSet user1 = new UserDataSet("Mike", 27);
        dbService.save(user1);

        UserDataSet user2 = dbService.read(1);

        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertEquals(user1.getAge(), user2.getAge());
    }

}