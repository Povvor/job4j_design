package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index = 0;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int localIndex = index;
        while (localIndex < data.length && data[localIndex] == null) {
            localIndex++;
        }
        return localIndex < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[index] == null) {
            index++;
        }
        return data[index++];
    }

}