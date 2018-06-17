package ru.otus.HW09.exceptions;

public class BadDataModelException extends Exception {
    public BadDataModelException() {

    }

    public BadDataModelException(String message) {
        super(message);
    }
}