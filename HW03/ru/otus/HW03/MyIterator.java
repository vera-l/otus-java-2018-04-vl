package ru.otus.HW03;

import java.util.List;
import java.util.Iterator;

class MyIterator<E> implements Iterator<E> {
    private List<E> list;
    private int current;

    public MyIterator(List<E> l) {
        list = l;
        current = 0;
    }

    public boolean hasNext() {
        return current < list.size();
    }

    public E next() {
        return list.get(current++);
    }

    public void remove() {
        list.remove(--current);
    }
}