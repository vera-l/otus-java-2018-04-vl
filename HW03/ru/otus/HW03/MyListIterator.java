package ru.otus.HW03;

import java.util.List;
import java.util.ListIterator;

class MyListIterator<E> extends MyIterator<E> implements ListIterator<E> {

    private List<E> list;
    private int current;

    public MyListIterator(List<E> l) {
        super(l);
    }

    public MyListIterator(int index, List<E> l) {
        super(l);
        current = index;
    }

    public void add(E e) {
        list.add(e);
    }

    public boolean hasPrevious() {
        return current != 0;
    }

    public int nextIndex() {
        return current + 1;
    }

    public E previous() {
        return list.get(--current);
    }

    public int previousIndex() {
        return current - 1;
    }

    public void remove() {
        list.remove(current);
    }

    public void set(E e) {
        list.set(current, e);
    }

}