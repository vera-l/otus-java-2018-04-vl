package ru.otus.HW05;

import ru.otus.HW05.testlib.TestLib;

class Main {

    public static void main(String... args) {
        TestLib.runTests("ru.otus.HW05.tests.MyTests1");
        TestLib.runTests("ru.otus.HW05.tests.MyTests2");

        TestLib.runTests("ru.otus.HW05.tests");
    }
}