package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> node = head;
            Node<T> newNode = new Node<>(value, null);
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        index = Objects.checkIndex(index, size);
        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> newHead = head.next;
        Node<T> oldHead = head;
        oldHead.next = null;
        head = newHead;
        size--;
        modCount++;
        return oldHead.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = node.item;
                node = node.next;
                return result;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}