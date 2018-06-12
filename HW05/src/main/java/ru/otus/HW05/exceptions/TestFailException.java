package ru.otus.HW05.exceptions;

public class TestFailException extends Exception {
    public TestFailException() {

    }

    public TestFailException(String message) {
        super(message);
    }
}