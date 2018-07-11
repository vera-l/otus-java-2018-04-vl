package ru.otus.HW06;

public class NoNeededSumException extends Exception {

    private final static String ERROR_SIGN = "\uD83D\uDEAB";
    private final static String MESSAGE_TEMPLATE = ERROR_SIGN +
        " Нельзя выдать данную сумму (%s) " + ERROR_SIGN;

    public NoNeededSumException(int sum) {
        super(String.format(MESSAGE_TEMPLATE, String.valueOf(sum)));
    }

}