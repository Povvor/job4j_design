package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
       Node<E> parentNode = findBy(parent).orElse(null);
       Node<E> childNode = findBy(child).orElse(null);
       if (parentNode != null && childNode == null) {
           parentNode.children.add(new Node<>(child));
           return true;
       }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(v -> v.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        return findByPredicate(v -> v.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                return Optional.of(element);
            }
            data.addAll(element.children);
        }
        return Optional.empty();
    }
    }