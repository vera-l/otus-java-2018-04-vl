package ru.otus.HW05.testlib;

import ru.otus.HW05.exceptions.TestFailException;

public class Asserts {

    private static void assertRun(boolean statement, String message) throws TestFailException {
        if(statement) {
            System.out.println("[OK] " + message);
            return;
        }
        throw new TestFailException("[FAIL] " + message);
    }

    public static <T> void assertEquals(T value, T definedValue) throws TestFailException {
        assertRun(value.equals(definedValue), value + " is equal to " + definedValue);
    }

    public static <T> void assertNotEquals(T value, T definedValue) throws TestFailException {
        assertRun(!value.equals(definedValue), value + " is not equal to " + definedValue);
    }

    public static void assertTrue(boolean value) throws TestFailException {
        assertRun(value == true, value + " is true");
    }

    public static void assertFalse(boolean value) throws TestFailException {
        assertRun(value == false, value + " is false");
    }

    public static void assertNull(Object value) throws TestFailException {
        assertRun(value == null, value + " is null");
    }

    public static void assertNotNull(Object value) throws TestFailException {
        assertRun(value != null, value + " is not null");
    }

}