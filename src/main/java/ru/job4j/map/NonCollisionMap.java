package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = getEntry(key);
        return  entry == null ? null : entry.value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> entry = getEntry(key);

        if (entry != null) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                   cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return  (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity *= 2;
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                newTable[indexFor(hash(Objects.hashCode(entry.key)))] = entry;
            }
        }
        table = newTable;

    }

    private MapEntry<K, V> getEntry(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            return null;
        }
        K foundKey = table[index].key;
        if (Objects.hashCode(key) == Objects.hashCode(foundKey) && Objects.equals(key, foundKey)) {
            return table[index];
        }
        return null;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
