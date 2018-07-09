package ru.otus.HW10;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.HW09.models.AddressDataSet;
import ru.otus.HW09.models.PhoneDataSet;
import ru.otus.HW09.models.UserDataSet;

import java.util.Arrays;

public class HibernateServiceTests {

    private DBServiceHibernateImpl dbService;

    @Before
    public void methodSetup() {
        dbService = new DBServiceHibernateImpl();
    }

    @Test
    public void usersShouldBeAdded() {
        UserDataSet user1 = new UserDataSet("Mike", 27);
        user1.setAddress(new AddressDataSet("Lenina str."));
        PhoneDataSet phone1 = new PhoneDataSet("+7 495 345-67-89");
        phone1.setUserDataSet(user1);
        PhoneDataSet phone2 = new PhoneDataSet("+7 905 123-34-65");
        phone2.setUserDataSet(user1);
        user1.setPhones(Arrays.asList(phone1, phone2));
        dbService.save(user1);

        UserDataSet user2 = dbService.readAll().get(0);

        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertEquals(user1.getAge(), user2.getAge());
        Assert.assertEquals(user1.getAddress().getStreet(), user2.getAddress().getStreet());
        Assert.assertEquals(
            user1.getPhones().get(0).getNumber(),
            user2.getPhones().get(0).getNumber()
        );
        Assert.assertEquals(
            user1.getPhones().get(1).getNumber(),
            user2.getPhones().get(1).getNumber()
        );
    }

}