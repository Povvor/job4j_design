package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    boolean isInitialOrder;
    int size;

    public T poll() {
        if (size <= 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (!isInitialOrder) {
            size--;
            return output.pop();
        } else {
            changeOrder();
            return poll();
        }
    }

    public void push(T value) {
        if (isInitialOrder) {
            input.push(value);
            size++;
        } else {
            changeOrder();
            push(value);
        }
    }

    public void changeOrder() {
        if (isInitialOrder) {
            for (int i = 0; i < size; i++) {
                output.push(input.pop());
            }
        } else {
            for (int i = 0; i < size; i++) {
                input.push(output.pop());
            }
        }
        isInitialOrder = !isInitialOrder;
    }
}