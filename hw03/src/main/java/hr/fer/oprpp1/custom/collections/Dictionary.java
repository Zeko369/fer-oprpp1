package hr.fer.oprpp1.custom.collections;

import java.util.Objects;

/**
 * Simple dictionary backed by ArrayIndexedCollection
 *
 * @param <K> key type
 * @param <V> value type
 * @author franzekan
 */
public class Dictionary<K, V> {
    /**
     * Item s in dictionary
     * @param <EK> key type
     * @param <EV> value type
     */
    private static class Entry<EK, EV> {
        private final EK key;
        private EV value;

        /**
         * Instantiates a new Entry.
         *
         * @param key   the key
         * @param value the value
         */
        Entry(EK key, EV value) {
            this.key = Objects.requireNonNull(key);
            this.value = value;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        public EK getKey() {
            return key;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public EV getValue() {
            return value;
        }

        /**
         * Sets value.
         *
         * @param value the value
         */
        public void setValue(EV value) {
            this.value = value;
        }
    }

    /**
     * Internal store
     */
    private final ArrayIndexedCollection<Entry<K, V>> entries;

    /**
     * Instantiates a new Dictionary.
     */
    public Dictionary() {
        this.entries = new ArrayIndexedCollection<>();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return entries.size();
    }

    /**
     * Clear.
     */
    public void clear() {
        entries.clear();
    }

    /**
     * Put element into dictionary, overwrites the existing element
     *
     * @param key   the key
     * @param value the value
     * @return null if key not found, old value if key found
     */
    public V put(K key, V value) {
        for (int i = 0; i < entries.size(); i++) {
            if (this.entries.get(i).getKey().equals(key)) {
                V oldValue = this.entries.get(i).getValue();
                this.entries.get(i).setValue(value);

                return oldValue;
            }
        }

        this.entries.add(new Entry<>(key, value));
        return null;
    }

    /**
     * Gets element by key
     *
     * @param key the key
     * @return element or null if element not found
     */
    public V get(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (this.entries.get(i).getKey().equals(key)) {
                return this.entries.get(i).getValue();
            }
        }

        return null;
    }

    /**
     * Remove element by key
     *
     * @param key the key
     * @return null if key not found, old value if key found
     */
    public V remove(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (this.entries.get(i).getKey().equals(key)) {
                V oldValue = this.entries.get(i).getValue();
                this.entries.remove(i);
                return oldValue;
            }
        }

        return null;
    }
}
