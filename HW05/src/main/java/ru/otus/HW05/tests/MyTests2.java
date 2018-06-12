package ru.otus.HW05.tests;

import java.util.Arrays;
import java.util.List;
import ru.otus.HW03.MyArrayList;
import ru.otus.HW05.exceptions.TestFailException;
import static ru.otus.HW05.testlib.Asserts.assertTrue;
import static ru.otus.HW05.testlib.Asserts.assertFalse;
import ru.otus.HW05.annotations.After;
import ru.otus.HW05.annotations.Before;
import ru.otus.HW05.annotations.Test;

public class MyTests2 {

    MyArrayList<Integer> list;

    @Before
    public void before() {
        System.out.println("Run BEFORE-method");
        list = new MyArrayList();
    }

    @After
    public void after() {
        System.out.println("Run AFTER-method");
    }

    @Test("При добавлении новых элементов все они должны обнаруживаться в списке")
    public void testElementsAddition() throws TestFailException {
        list.add(10);
        list.add(20);
        list.add(30);
        assertTrue(list.contains(10));
        assertFalse(list.contains(50));
    }

    @Test("При добавлении элементов из другого источника все они должны обнаруживаться в списке")
    public void testElementsFromAnotherListAddition() throws TestFailException {
        List anotherList = Arrays.asList(1, 2, 5, 9, 11);
        list.add(10);
        assertTrue(list.contains(10));
        assertFalse(list.containsAll(anotherList));
        list.addAll(anotherList);
        assertTrue(list.containsAll(anotherList));
        assertFalse(list.contains(100));
    }
}
