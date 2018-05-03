package ru.otus.HW0201;

import java.util.ArrayList;
import java.util.Calendar;
import java.math.BigDecimal;

public class Main {

    public static void main(String... args) {
        printObjectSize("1", 1);
        printObjectSize("1L", 1L);
        printObjectSize("1.123F", 1.123F);
        printObjectSize("1.123", 1.123);
        printObjectSize("true", true);
        printObjectSize("'a'", 'a');
        printDelimeter();
        printObjectSize("new Object()", new Object());
        printObjectSize("new A()", new A());
        printObjectSize("new A(123, \"456\")", new A(123, "456"));
        printObjectSize("Calendar.getInstance()", Calendar.getInstance());
        printObjectSize("new BigDecimal(\"99999.999\")", new BigDecimal("99999.999"));
        printObjectSize("new ArrayList<String>()", new ArrayList<String>());
        printDelimeter();
        printObjectSize("new Integer[1]", new Integer[1]);
        printObjectSize("new Integer[2]", new Integer[2]);
        printObjectSize("new Integer[3]", new Integer[3]);
        printObjectSize("new Integer[4]", new Integer[4]);
        printObjectSize("new Integer[5]", new Integer[5]);
        printObjectSize("new Integer[8]", new Integer[8]);
        printObjectSize("new Integer[10]", new Integer[10]);
        printObjectSize("new Integer[16]", new Integer[16]);
        printObjectSize("new Integer[100]", new Integer[100]);
        printObjectSize("new Integer[1_000]", new Integer[1_000]);
        printObjectSize("new Integer[10_000]", new Integer[10_000]);
        printObjectSize("new Integer[100_000]", new Integer[100_000]);
        printDelimeter();
        printObjectSize("\"\"", "");
        printObjectSize("\"a\"", "a");
        printObjectSize("\"ab\"", "ab");
        printObjectSize("\"abc\"", "abc");
        printObjectSize("\"abcd\"", "abcd");
        printObjectSize("\"abcdefhij\"", "abcdefhij");
    }

    private static void printObjectSize(String name, Object obj) {
        System.out.println(
            String.format(
                "%-30s %s",
                name,
                AgentMemoryCounter.getSize(obj)
            )
        );
    }

    private static void printObjectSize(Object obj) {
        printObjectSize(obj.getClass().getSimpleName(), obj);
    }

    private static void printDelimeter() {
        System.out.println("---------------------------------------");
    }

}

class A {
    Integer field1;
    String field2;

    public A() {}

    public A(Integer field1, String field2) {
        field1 = field1;
        field2 = field2;
    }
    
}
