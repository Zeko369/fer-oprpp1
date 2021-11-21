package hr.fer.oprpp1.custom.collections;

import java.util.*;
import java.util.function.Consumer;

/**
 * SimpleHashtable is a simple implementation of Hashtable.
 *
 * @param <K> key type
 * @param <V> value type
 * @author franzekan
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Size of the hashtable
     */
    private int size;

    /**
     * Internal array of the hashtable
     */
    private TableEntry<K, V>[] table;

    /**
     * Modification count
     */
    private long modificationCount = 0;

    /**
     * Internal implementation of an Iterator
     */
    private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {
        private long localModificationCount;

        private int slot;
        private int index;

        private boolean removed = false;
        private TableEntry<K, V> last;

        /**
         * Instantiates a new Iterator.
         */
        public IteratorImpl() {
            this.localModificationCount = modificationCount;
        }

        private void checkConcurrent() {
            if (this.localModificationCount != modificationCount) {
                throw new ConcurrentModificationException();
            }

        }

        /**
         * Checks if there is a next element
         *
         * @return if there is a next element
         * @throws ConcurrentModificationException if the modification count is different
         */
        public boolean hasNext() {
            this.checkConcurrent();

            TableEntry<K, V> curr = table[slot];
            for (int i = 0; i < this.index; i++) {
                if (curr == null) {
                    break;
                }

                curr = curr.next;
            }

            if (curr != null) {
                return true;
            }

            for (int i = this.slot + 1; i < table.length; i++) {
                if (table[i] != null) {
                    return true;
                }
            }

            return false;
        }

        /**
         * Gets the next element
         *
         * @return next element
         * @throws NoSuchElementException          if there is no next element
         * @throws ConcurrentModificationException if the modification count is different
         */
        public TableEntry<K, V> next() {
            this.checkConcurrent();

            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            while (this.slot < table.length) {
                TableEntry<K, V> curr = table[slot];

                for (int i = 0; i < this.index; i++) {
                    if (curr == null) {
                        break;
                    }

                    curr = curr.next;
                }

                if (curr == null) {
                    this.slot++;
                    this.index = 0;

                    continue;
                }

                this.index++;
                this.last = curr;
                this.removed = false;

                return this.last;
            }

            throw new NoSuchElementException();
        }

        /**
         * Removes last element from the iterator
         *
         * @throws IllegalStateException if the last element was already removed
         */
        public void remove() {
            this.checkConcurrent();

            if (this.removed) {
                throw new IllegalStateException();
            }

            SimpleHashtable.this.remove(this.last.getKey());
            this.index--;
            this.removed = true;
            this.localModificationCount++;
        }
    }

    /**
     * Instantiates a new Simple hashtable with given capacity.
     *
     * @param capacity the capacity
     * @throws NullPointerException if capacity is null
     */
    @SuppressWarnings("unchecked")
    public SimpleHashtable(int capacity) {
        this.table = (TableEntry<K, V>[]) new TableEntry[capacity];
        this.size = 0;
    }

    /**
     * Instantiates a new Simple hashtable with default capacity.
     */
    public SimpleHashtable() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public Iterator<TableEntry<K, V>> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void forEach(Consumer<? super TableEntry<K, V>> action) {
        Iterable.super.forEach(action);
    }

    /**
     * Simple class representing a single entry in the hashtable.
     *
     * @param <TK> key type
     * @param <TV> value type
     * @author franzekan
     */
    public static class TableEntry<TK, TV> {
        private final TK key;
        private TV value;

        private TableEntry<TK, TV> next;

        /**
         * Instantiates a new Table entry.
         *
         * @param key   the key
         * @param value the value
         */
        public TableEntry(TK key, TV value) {
            this.key = Objects.requireNonNull(key);
            this.value = value;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        public TK getKey() {
            return key;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public TV getValue() {
            return value;
        }

        /**
         * Sets value.
         *
         * @param value the value
         */
        public void setValue(TV value) {
            this.value = value;
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % this.table.length);
    }

    private boolean shouldResize() {
        return ((float) this.size / (float) this.table.length) >= DEFAULT_LOAD_FACTOR;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        TableEntry<K, V>[] tmp = this.toArray();
        this.table = (TableEntry<K, V>[]) new TableEntry[this.table.length * 2];
        this.size = 0;

        for (TableEntry<K, V> elem : tmp) {
            this.put(elem.getKey(), elem.getValue());
        }
    }

    /**
     * Add item to the hashtable.
     *
     * @param key   the key
     * @param value the value
     * @return the v
     * @throws NullPointerException if key is null
     */
    public V put(K key, V value) {
        TableEntry<K, V> entry = new TableEntry<>(Objects.requireNonNull(key), value);

        if (this.shouldResize()) {
            this.resize();
        }

        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = entry;
        } else {
            TableEntry<K, V> current = table[index];
            while (current.next != null && !current.getKey().equals(key)) {
                current = current.next;
            }

            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }

            current.next = entry;
        }

        this.size++;
        this.modificationCount++;
        return null;
    }

    /**
     * Get by key
     *
     * @param key the key
     * @return the v
     */
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = getIndex(key);
        if (table[index] == null) {
            return null;
        }

        TableEntry<K, V> current = table[index];
        while (!current.getKey().equals(key) && current.next != null) {
            current = current.next;
        }

        if (current.getKey().equals(key)) {
            return current.getValue();
        }

        return null;
    }

    /**
     * Remove by key
     *
     * @param key the key
     * @return the v
     */
    public V remove(K key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return null;
        }

        TableEntry<K, V> current = table[index];
        if (current.getKey().equals(key)) {
            V oldValue = current.getValue();
            this.table[index] = current.next;
            this.size--;
            this.modificationCount++;

            return oldValue;
        }

        while (current.next != null && !current.next.getKey().equals(key)) {
            current = current.next;
        }

        if (current.next != null) {
            V oldValue = current.next.getValue();
            if (current.next.next != null) {
                current.next = current.next.next;
            }

            this.size--;
            this.modificationCount++;

            return oldValue;
        }

        return null;
    }

    /**
     * Check if the key is in the hashtable.
     *
     * @param key the key
     * @return the boolean
     */
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }

        return this.get((K) key) != null;
    }

    /**
     * Check if the value is in the hashtable.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean containsValue(Object value) {
        for (TableEntry<K, V> kvTableEntry : table) {
            TableEntry<K, V> current = kvTableEntry;
            while (current != null && !current.getValue().equals(value)) {
                current = current.next;
            }

            if (current != null && current.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Clear all elements.
     */
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            this.table[i] = null;
        }

        this.size = 0;
        this.modificationCount++;
    }

    /**
     * Get size
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    /**
     * Check if the hashtable is empty
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Convert all elements to an array
     *
     * @return the table entry [ ]
     */
    @SuppressWarnings("unchecked")
    public TableEntry<K, V>[] toArray() {
        TableEntry<K, V>[] array = (TableEntry<K, V>[]) new TableEntry[this.size];
        int index = 0;

        for (TableEntry<K, V> kvTableEntry : table) {
            TableEntry<K, V> current = kvTableEntry;
            while (current != null) {
                array[index++] = current;
                current = current.next;
            }
        }

        return array;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        TableEntry<K, V>[] array = this.toArray();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].getKey());
            sb.append("=");
            sb.append(array[i].getValue());

            if (i < this.size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
