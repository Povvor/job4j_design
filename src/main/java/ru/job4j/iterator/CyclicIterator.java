package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int point;

    public CyclicIterator(List<T> data) {
        this.data = data;

    }

    @Override
    public boolean hasNext() {
        if (point >= data.size()) {
            point = 0;
        }
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(point++);
    }
}