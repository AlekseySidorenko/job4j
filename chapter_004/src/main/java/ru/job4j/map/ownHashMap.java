package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ownHashMap<K, V> implements Iterable<ownHashMap.Entry<K, V>> {

    private static final int DEFAULT_INITIAL_CAPACITY = 2;
    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private Object[] table;
    private int size;
    private int modCount;

    public ownHashMap() {
        this.table = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public boolean insert(K key, V value) {
        if (table.length == size) {
            resize();
        }
        if (key == null) {
            insertForNullKey(value);
            return true;
        }
        int index = hash(key) & (table.length - 1);
        Entry<K, V> e = (Entry<K, V>)table[index];
        if (e == null) {
            addEntry(hash(key), key, value, index);
        } else {
            if (e.getKey().equals(key)) {
                System.out.println("Entry with same key is already in map");
                return false;
            }
            e.next = new Entry<>(hash(key), key, value);
        }
        size++;
        modCount++;
        return true;
    }

    private boolean insertForNullKey(V value) {
        if (table[0] != null) {
            System.out.println("Entry with same key is already in map");
            return false;
        }
        table[0] = new Entry(0, null, value);
        size++;
        modCount++;
        return true;
    }

    private void addEntry(int hash, K key, V value, int index) {
        table[index] = new Entry<>(hash, key, value);
    }

    public V get(Object key) {
        Entry<K,V> e;
        return (e = getEntry(hash(key), key)) == null ? null : e.value;
    }

    Entry<K,V> getEntry(int hash, Object key) {
        Entry<K,V> first = (Entry<K, V>) table[(table.length - 1) & hash];
        Entry<K,V> e;
        if (first != null) {
            if (first.hash == hash && first.key == key || (key != null && key.equals(first.key))) {
                return first;
            }

            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash && key == e.key || (key != null && key.equals(first.key))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    private Object[] resize() {
        if (table.length == (1 << 30)) {
            return table;
        }

        Object[] oldTable = table;
        table = new Object[oldTable.length * 2];
        for (Object element : oldTable) {
            Entry<K, V> e = (Entry<K, V>)element;
            this.insert(e.getKey(), e.getValue());
        }
        return table;
    }

    @Override
    public Iterator<ownHashMap.Entry<K,V>> iterator() {
        return new EntryIterator() {
        };
    }

    abstract class ownHashMapIterator {
        Entry<K,V>[] t = (Entry<K,V>[])table;
        Entry<K,V> next;
        Entry<K,V> current;
        int expectedModCount;
        int index;

        ownHashMapIterator() {
            expectedModCount = modCount;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        public final Entry<K,V> nextEntry() {
            Entry<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t != null)) {
                do {} while (index < table.length && (next = t[index++]) == null);
            }
            return e;
        }
    }

    public class EntryIterator extends ownHashMapIterator implements Iterator<ownHashMap.Entry<K,V>> {
        public final ownHashMap.Entry<K,V> next() { return nextEntry(); }
    }

    static class Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Entry<K, V> next;

        Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}