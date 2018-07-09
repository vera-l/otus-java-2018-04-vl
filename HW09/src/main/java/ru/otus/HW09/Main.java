package ru.otus.HW09;

import ru.otus.HW09.models.UserDataSet;

class Main  {
    public static long NOT_EXIST_ID = 100000000;

    public static void main(String... args) {

        DBService dbService = new DBServiceImpl();

        // Add users
        dbService.save(new UserDataSet("John", 25));
        dbService.save(new UserDataSet("Maria", 31));
        dbService.save(new UserDataSet("Peter", 43));

        // Get users
        System.out.println(dbService.read(2));
        System.out.println(dbService.read(3));
        System.out.println(dbService.read(4));
        System.out.println(dbService.read(NOT_EXIST_ID));

        // Get all users
        System.out.println(dbService.readAll());
    }

}