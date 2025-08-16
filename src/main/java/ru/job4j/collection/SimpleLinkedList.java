package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> node = head;
            Node<E> newNode = new Node<>(value, null);
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
            }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        index = Objects.checkIndex(index, size);
        Node<E> node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            SimpleLinkedList.Node<E> node = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = node.item;
                node = node.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}