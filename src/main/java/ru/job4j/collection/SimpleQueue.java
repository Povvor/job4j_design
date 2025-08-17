package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int size;
    private int inputSize;

    public T poll() {
        if (size <= 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        checkStacks();
        size--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        size++;
        inputSize++;
    }

    private void checkStacks() {
        if (size - inputSize <= 0) {
            for (int i = 0; i < inputSize; i++) {
                output.push(input.pop());
            }
            inputSize = 0;
        }
    }
}