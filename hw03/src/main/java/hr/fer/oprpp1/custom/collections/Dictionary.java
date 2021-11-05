package hr.fer.oprpp1.custom.collections;

import java.util.Objects;

public class Dictionary<K, V> {
    private static class Entry<EK, EV> {
        private final EK key;
        private EV value;

        Entry(EK key, EV value) {
            this.key = Objects.requireNonNull(key);
            this.value = value;
        }

        public EK getKey() {
            return key;
        }

        public EV getValue() {
            return value;
        }

        public void setValue(EV value) {
            this.value = value;
        }
    }

    private final ArrayIndexedCollection<Entry<K, V>> entries;

    public Dictionary() {
        this.entries = new ArrayIndexedCollection<>();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public int size() {
        return entries.size();
    }

    public void clear() {
        entries.clear();
    }

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

    public V get(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (this.entries.get(i).getKey().equals(key)) {
                return this.entries.get(i).getValue();
            }
        }

        return null;
    }

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
