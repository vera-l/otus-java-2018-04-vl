package ru.otus.HW03;

import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.lang.IndexOutOfBoundsException;

class MyArrayList<E> implements List<E> {
    private final static int DEFAULT_CAPACITY = 5;
    private final static int CAPACITY_MULTIPLIER = 2;

    private int size;
    private int capacity;
    private Object[] data;


    public MyArrayList() {
        setDefaultList();
    }

    public MyArrayList(E[] source) {
        this();
        data = source;
        size = source.length;
    }

    public boolean add(E item) {
        checkCapacity(1);

        data[size++] = item;
        return true;
    }

    public void add(int index, E element) {
        checkIndex(index);
        checkCapacity(1);

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
    }

    public boolean addAll(Collection<? extends E> c) {
        checkCapacity(c.size());

        for (E item : c) {
            add(item);
        }

        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        checkCapacity(c.size());

        System.arraycopy(data, index, data, index + c.size(), size - index);

        for (E item : c) {
            add(index++, item);
        }

        return true;
    }

    public void clear() {
        setDefaultList();
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    public boolean equals(MyArrayList thatList) {
        if (this == thatList) {
            return true;
        }
        if (thatList == null || getClass() != thatList.getClass()) {
            return false;
        }
        if (thatList.size() != size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (get(i) != thatList.get(i)) {
                return false;
            }
        }

        return true;
    }

    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (data[i] == item) {
                return i;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public MyIterator<E> iterator() {
        return new MyIterator<E>(this);
    }

    public E get(int index) {
        checkIndex(index);

        return (E) data[index];
    }

    public int lastIndexOf(Object item) {
        for (int i = size - 1; i <= 0; i--) {
            if (data[i] == item) {
                return i;
            }
        }

        return -1;
    }

    public MyListIterator<E> listIterator() {
        return new MyListIterator<E>(this);
    }

    public MyListIterator<E> listIterator(int index) {
        return new MyListIterator<E>(index, this);
    }

    public boolean remove(Object item) {
        boolean result = false;

        for (int i = 0; i < size(); i++) {
            if (get(i) == item) {
                remove(i--);
                result = true;
            }
        }

        return result;
    }

    public E remove(int index) {
        checkIndex(index);

        E item = (E) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;

        return item;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            remove(item);
        }

        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            if (!c.contains(get(i))) {
                remove(i--);
            }
        }

        return true;
    }

    public E set(int index, E element) {
        checkIndex(index);

        Object old = data[index];
        data[index] = element;
        return (E) old;
    }

    public int size() {
        return size;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        int count = toIndex - fromIndex;
        Object[] result = new Object[count];
        System.arraycopy(data, fromIndex, result, 0, count);

        return new MyArrayList<E>((E[]) result);
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }

    public <E> E[] toArray(E[] a) {
        Object[] result = new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return (E[]) result;
    }

    public String toString() {
        String result = Arrays.stream(data)
                              .map(i -> i == null ? "_" : i.toString())
                              .collect(Collectors.joining(", "));
        return "[" + result + "]";
    }

    public int capacity() {
        return capacity;
    }

    private void checkCapacity(int delta) {
        if (delta <= 0) {
            return;
        }

        int newSize = size + delta;

        if (newSize <= capacity) {
            return;
        }

        while (newSize > capacity) {
            capacity *= CAPACITY_MULTIPLIER;
        }

        Object[] new_data = new Object[capacity];
        System.arraycopy(data, 0, new_data, 0, size);
        data = new_data;
    }

    private void setDefaultList() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        data = new Object[capacity];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

}