package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            grow();
        }
        modCount++;
        container[size++] = value;
    }

    private void grow() {
         T[] newContainer = Arrays.copyOf(container, container.length * 2);
         container = container.length == 0 ? (T[]) new Object[1] : newContainer;

    }

    @Override
    public T set(int index, T newValue) {
        int i = Objects.checkIndex(index, size);
        T oldValue = container[i];
        container[i] = newValue;
        return oldValue;

    }

    @Override
    public T remove(int index) {
        int i = Objects.checkIndex(index, size);
        T removed = container[i];
        int movedNum = size - i - 1;
        System.arraycopy(container, i + 1, container, i,  movedNum);
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        int i = Objects.checkIndex(index, size);
        return container[i];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int cursor;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}