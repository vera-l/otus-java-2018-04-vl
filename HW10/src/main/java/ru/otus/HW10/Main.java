package ru.otus.HW10;

import ru.otus.HW09.models.AddressDataSet;
import ru.otus.HW09.models.PhoneDataSet;
import ru.otus.HW09.models.UserDataSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

class Main  {

    public static long NOT_EXIST_ID = 100000000;

    public static void main(String... args) throws ClassNotFoundException, SQLException,
        IOException {

        DBServiceHibernateImpl dbService = new DBServiceHibernateImpl();

        // Add users
        UserDataSet user1 = new UserDataSet("John", 25);
        user1.setAddress(new AddressDataSet("Svobody str."));
        dbService.save(user1);
        UserDataSet user2 = new UserDataSet("Maria", 31);
        PhoneDataSet phone1 = new PhoneDataSet("+7 495 900-20-12");
        phone1.setUserDataSet(user2);
        PhoneDataSet phone2 = new PhoneDataSet("+7 905 123-45-67");
        phone2.setUserDataSet(user2);
        user2.setPhones(
            Arrays.asList(
                phone1,
                phone2
            )
        );
        dbService.save(user2);
        dbService.save(new UserDataSet("Peter", 43));

        // Get users
        System.out.println(dbService.read(1));
        System.out.println(dbService.read(2));
        System.out.println(dbService.read(3));
        System.out.println(dbService.read(NOT_EXIST_ID));

        // Get all users
        System.out.print(dbService.readAll());

        dbService.shutdown();
    }

}