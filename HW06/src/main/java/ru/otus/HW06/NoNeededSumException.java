package ru.otus.HW06;

public class NoNeededSumException extends Exception {
    public NoNeededSumException() {

    }

    public NoNeededSumException(String message) {
        super(message);
    }
}