package ru.otus.HW11;

import ru.otus.HW09.models.UserDataSet;
import java.io.IOException;
import java.sql.SQLException;

class Main  {

    public static long NOT_EXIST_ID = 100000000;

    public static void main(String... args) throws ClassNotFoundException, SQLException,
        IOException {

        DBServiceCachedImpl dbService = new DBServiceCachedImpl();

        // Add users
        UserDataSet user1 = new UserDataSet("John", 25);
        dbService.save(user1);
        UserDataSet user2 = new UserDataSet("Maria", 31);
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