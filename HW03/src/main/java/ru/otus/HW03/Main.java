package ru.otus.HW03;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Main {
    private static MyArrayList<String> list = new MyArrayList<>();

    public static void main(String... args) {
        System.out.println("> Add 1 to list");
        list.add("1");
        printListInfo();

        System.out.println("> Add 2 to list");
        list.add("2");
        printListInfo();

        System.out.println("> Add 3 to list");
        list.add("3");
        printListInfo();

        System.out.println("> Add 4 to list");
        list.add("4");
        printListInfo();

        System.out.println("> Add 5 to list");
        list.add("5");
        printListInfo();

        System.out.println("> Add 6 to list");
        list.add("6");
        printListInfo();

        System.out.println("> Remove item #1 from list");
        list.remove(1);
        printListInfo();

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("3");
        hashSet.add("4");
        hashSet.add("5");
        hashSet.add("6");
        hashSet.add("7");
        hashSet.add("8");
        hashSet.add("9");
        System.out.println("> Add HashSet{\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"} to list");
        list.addAll(hashSet);
        printListInfo();

        System.out.println("> Remove item #8 from list");
        list.remove(8);
        printListInfo();

        System.out.println("> Remove object \"3\" from list");
        list.remove("3");
        printListInfo();

        System.out.println("> Is this list contains \"6\"?");
        System.out.println(list.contains("6"));

        System.out.println("> Is this list contains \"Some string\"?");
        System.out.println(list.contains("Some string"));

        System.out.println("> Is this list contains all objects of hashSet we added?");
        System.out.println(list.containsAll(hashSet));

        System.out.println("> Get an iterator and print all elements");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("> Retain in list only our hashSet elements");
        list.retainAll(hashSet);
        printListInfo();

        System.out.println("> Remove from list all elements contains in our hashSet");
        list.removeAll(hashSet);
        printListInfo();

        System.out.println("> Add 3 to list");
        list.add("3");
        printListInfo();

        System.out.println("> Add 4 to list");
        list.add("4");
        printListInfo();

        System.out.println("> Convert this list to this array:");
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println("> Compare list to {\"3\",\"4\"}");
        System.out.println(
            list.equals(
                new MyArrayList(new String[]{"3", "4"})
            )
        );

        System.out.println("> Compare list to {\"5\",\"4\"}");
        System.out.println(
            list.equals(
                new MyArrayList(new String[]{"5", "4"})
            )
        );

        System.out.println("> Add \"a\", \"b\" by Collections.addAll()");
        Collections.addAll(list, "a", "b");
        printListInfo();

        ArrayList<String> anotherList = new ArrayList<>();
        anotherList.add("7");
        anotherList.add("5");
        anotherList.add("3");
        System.out.println("Copy items of another list by Collections.copy()");
        Collections.copy(list, anotherList);
        printListInfo();

        System.out.println("> Sort list elements by Collections.sort()");
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        printListInfo();

        System.out.println("> Clear list");
        list.clear();
        printListInfo();

    }

    private static void printListInfo() {
        System.out.println(
            String.format(
                "%s | size %s | capacity %s",
                list.toString(),
                list.size(),
                list.capacity()
            )
        );
    }

    private static void printListInfo(String message, MyArrayList list) {
        System.out.println(
            String.format(
                "%s: ... %s | size %s | capacity %s",
                list.toString(),
                list.size(),
                list.capacity()
            )
        );
    }

}
