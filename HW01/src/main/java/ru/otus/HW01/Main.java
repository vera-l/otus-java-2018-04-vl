package ru.otus.HW01;

import com.google.common.base.Strings;

public class Main {
    private static final String part = "ABC";
    private static final int count = 10;

    public static void main(String... args) {
        String result = Strings.repeat(part, count);

        System.out.println(result);
    }
}
