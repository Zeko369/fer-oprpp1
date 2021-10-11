package hr.fer.oprpp1.custom.collections;

import java.util.Arrays;

public class ArrayIndexedCollection extends Collection {
    private int size = 0;
    private Object[] elements;

    public int size() {
        return this.size;
    }

    public ArrayIndexedCollection(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Size can't be less than 1");
        }

        this.elements = new Object[capacity];
    }

    public ArrayIndexedCollection() {
        this(16);
    }

    private static int getSize(Collection initCollection, int capacity) {
        if (initCollection == null) {
            throw new NullPointerException();
        }

        return Math.max(capacity, initCollection.size());
    }

    public ArrayIndexedCollection(Collection initCollection, int capacity) {
        this(ArrayIndexedCollection.getSize(initCollection, capacity));
        this.addAll(initCollection);
    }

    public ArrayIndexedCollection(Collection initCollection) {
        this(initCollection, -1);
    }

    private void resize() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    @Override
    void forEach(Processor processor) {
        for (int i = 0; i < this.size; i++) {
            processor.process(this.elements[i]);
        }
    }

    public void add(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (this.size >= this.elements.length) {
            this.resize();
        }

        this.elements[this.size++] = value;
    }

    public Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        return this.elements[index];
    }

    public void clear() {
        Arrays.fill(this.elements, null);
        this.size = 0;
    }

    // TODO:
    void insert(Object value, int position) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (position < 0 || position > this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (this.elements.length == this.size) {
            this.resize();
        }

        for (int i = this.size; i > position; i--) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[position] = value;
        this.size++;
    }

    int indexOf(Object value) {
        for (int i = 0; i < this.elements.length; i++) {
            if (this.elements[i] == null) {
                break;
            }

            if (this.elements[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < this.elements.length - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.size--;
    }

    Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override
    boolean contains(Object value) {
        return this.indexOf(value) != -1;
    }
}
