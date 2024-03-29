package hr.fer.oprpp1.custom.collections;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Collection implementation using an array[] as a storage backend
 *
 * @param <T> the type parameter
 * @author franzekan
 * @version 1.0
 */
public class ArrayIndexedCollection<T> implements List<T> {
    private long modificationCount = 0;

    private static class ArrayIndexedCollectionElementsGetter<K> implements ElementsGetter<K> {
        private int index;
        private final ArrayIndexedCollection<K> collection;
        private final long savedModificationCount;

        /**
         * Instantiates a new Array indexed collection elements getter.
         *
         * @param collection the collection
         */
        public ArrayIndexedCollectionElementsGetter(ArrayIndexedCollection<K> collection) {
            this.collection = collection;
            this.savedModificationCount = collection.modificationCount;
            this.index = 0;
        }

        private void checkConcurrent() {
            if (this.savedModificationCount != this.collection.modificationCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNextElement() {
            checkConcurrent();
            return this.index < this.collection.size();
        }

        @Override
        public K getNextElement() {
            checkConcurrent();
            if (!this.hasNextElement()) {
                throw new NoSuchElementException();
            }

            return this.collection.get(this.index++);
        }
    }

    // TODO: move down
    public ElementsGetter<T> createElementsGetter() {
        return new ArrayIndexedCollectionElementsGetter<>(this);
    }

    /**
     * Count of elements stored
     */
    private int size = 0;

    /**
     * Internal array store
     */
    private T[] elements;

    /**
     * DEFAULT SIZE
     */
    public static final int DEFAULT_SIZE = 16;

    /**
     * Simple constructor without any params
     */
    public ArrayIndexedCollection() {
        this(ArrayIndexedCollection.DEFAULT_SIZE);
    }


    /**
     * Constructor that is used to preallocate specified size
     *
     * @param capacity initial size of internal array
     * @throws IllegalArgumentException if size is less than 1
     */
    @SuppressWarnings("unchecked")
    public ArrayIndexedCollection(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Size can't be less than 1");
        }

        this.elements = (T[]) new Object[capacity];
    }

    /**
     * Constructor that takes an existing collection and a specified initial size
     *
     * @param initCollection existing collection
     * @param capacity       the capacity
     * @throws NullPointerException     if initCollection is null
     * @throws IllegalArgumentException if initCollection has no elements and capacity is 0
     */
    public ArrayIndexedCollection(Collection<T> initCollection, int capacity) {
        this(Math.max(capacity, Objects.requireNonNull(initCollection).size()));
        this.addAll(initCollection);
    }

    /**
     * Constructor that takes an existing collection
     *
     * @param initCollection existing collection
     * @throws NullPointerException     if initCollection is null
     * @throws IllegalArgumentException if initCollection has no elements
     */
    public ArrayIndexedCollection(Collection<T> initCollection) {
        this(initCollection, -1);
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Internal method used to resize the internal array
     */
    private void resize() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        this.modificationCount++;
    }

    @Override
    public void forEach(Processor<T> processor) {
        for (int i = 0; i < this.size; i++) {
            processor.process(this.elements[i]);
        }
    }

    /**
     * Adds the value to the list
     *
     * @param value value to be added
     * @throws NullPointerException if value is null
     */
    @Override
    public void add(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (this.size >= this.elements.length) {
            this.resize();
        }

        this.elements[this.size++] = value;
        this.modificationCount++;
    }

    /**
     * Gets element by index
     *
     * @param index index
     * @return element at that index
     * @throws IndexOutOfBoundsException if index our of range
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        return this.elements[index];
    }

    @Override
    public void clear() {
        Arrays.fill(this.elements, null);

        this.size = 0;
        this.modificationCount++;
    }

    /**
     * Inserts element at specific position in array
     *
     * @param value    new element
     * @param position new element position
     * @throws NullPointerException      if value is null
     * @throws IndexOutOfBoundsException if position is less than 0 or more than size
     */
    public void insert(T value, int position) {
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
        this.modificationCount++;
    }

    /**
     * Get index of element in the array or -1 if not found
     *
     * @param value element to get index for
     * @return index in array or -1 if not found
     */
    public int indexOf(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

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

    /**
     * Remove by index
     *
     * @param index index of element to remove
     * @throws IndexOutOfBoundsException if index out of range
     */
    public void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < this.elements.length - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.size--;
        this.modificationCount++;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override
    public boolean contains(Object value) {
        return this.indexOf(value) != -1;
    }

    @Override
    public boolean remove(T value) {
        this.remove(this.indexOf(value));
        return true;
    }
}
