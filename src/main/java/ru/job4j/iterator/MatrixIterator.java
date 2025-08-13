package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
        row = 0;
        column = 0;
    }

    @Override
    public boolean hasNext() {
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        if (row >= data.length) {
            return false;
        }
        while (data[row].length == 0 && row < data.length - 1) {
            row++;
        }
        return data[row].length > 0 && column <= data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}