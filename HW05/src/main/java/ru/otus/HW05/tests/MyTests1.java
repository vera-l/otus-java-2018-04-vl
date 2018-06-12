package ru.otus.HW05.tests;

import java.util.Arrays;
import ru.otus.HW03.MyArrayList;
import ru.otus.HW05.exceptions.TestFailException;
import static ru.otus.HW05.testlib.Asserts.assertEquals;
import ru.otus.HW05.annotations.After;
import ru.otus.HW05.annotations.Before;
import ru.otus.HW05.annotations.Test;

public class MyTests1 {

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

    @Test("При добавлении новых элементов количество элементов должно увеличиваться")
    public void testElementsAddition() throws TestFailException {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(list.size(), 3);
    }

    @Test("При добавлении элементов из другого источника количество элементов должно увеличиваться")
    public void testElementsFromAnotherListAddition() throws TestFailException {
        list.add(10);
        assertEquals(list.size(), 1);
        list.addAll(Arrays.asList(1, 2, 5, 9, 11));
        assertEquals(list.size(), 6);
    }
}
