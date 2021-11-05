package hr.fer.oprpp1.custom.collections;

import java.util.Arrays;
import java.util.Objects;

public class SimpleHashtable<K, V> {
//    public static void main(String[] args) {
//        // create collection:
//        SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
//
//        // fill data:
//        examMarks.put("Ivana", 2);
//
//        Integer ivanaGrade = examMarks.get("Ivana");
//        System.out.println("Ivana's exam grade is: " + ivanaGrade); // writes: 2
//
//        examMarks.put("Ante", 2);
//        examMarks.put("Jasna", 2);
//        examMarks.put("Kristina", 5);
//        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
//
//        // query collection:
//        Integer kristinaGrade = examMarks.get("Kristina");
//        System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
//
//        ivanaGrade = examMarks.get("Ivana");
//        System.out.println("Ivana's exam grade is: " + ivanaGrade); // writes: 5
//
//        // What is collection's size? Must be four!
//        System.out.println("Number of stored pairs: " + examMarks.size()); // writes: 4
//
//        System.out.println("---------------------------------");
//
//        examMarks.remove("Ante");
//        examMarks.remove("Ivana");
//        examMarks.remove("Jasna");
//
//        kristinaGrade = examMarks.get("Kristina");
//        System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
//
//        ivanaGrade = examMarks.get("Ivana");
//        System.out.println("Ivana's exam grade is: " + ivanaGrade); // writes: 5
//
//        // What is collection's size? Must be four!
//        System.out.println("Number of stored pairs: " + examMarks.size()); // writes: 4
//    }

    private static final int DEFAULT_CAPACITY = 16;

    private int size;
    private TableEntry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public SimpleHashtable(int capacity) {
        this.table = (TableEntry<K, V>[]) new TableEntry[capacity];
        this.size = 0;
    }

    public SimpleHashtable() {
        this(DEFAULT_CAPACITY);
    }

    public static class TableEntry<TK, TV> {
        private final TK key;
        private TV value;

        private TableEntry<TK, TV> next;

        public TableEntry(TK key, TV value) {
            this.key = Objects.requireNonNull(key);
            this.value = value;
        }

        public TK getKey() {
            return key;
        }

        public TV getValue() {
            return value;
        }

        public void setValue(TV value) {
            this.value = value;
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % this.table.length);
    }

    private boolean shouldResize() {
        return ((float) this.size / (float) this.table.length) >= 0.75;
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

        size++;
        return null;
    }

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

            return oldValue;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }

        return this.get((K) key) != null;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            TableEntry<K, V> current = table[i];
            while (current != null && !current.getValue().equals(value)) {
                current = current.next;
            }

            if (current != null && current.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) {
            this.table[i] = null;
        }

        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    public TableEntry<K, V>[] toArray() {
        TableEntry<K, V>[] array = (TableEntry<K, V>[]) new TableEntry[this.size];
        int index = 0;

        for (int i = 0; i < table.length; i++) {
            TableEntry<K, V> current = table[i];
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
