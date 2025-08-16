package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removed = container[index];
        int movedNum = size - index - 1;
        System.arraycopy(container, index + 1, container, index,  movedNum);
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
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