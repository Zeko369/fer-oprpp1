package hr.fer.oprpp1.custom.collections;

/**
 * "abstract" base class for every custom Object collection we make
 *
 * @param <T> the type parameter
 * @author franzekan
 * @version 1.0
 */
public interface Collection<T> {
    // NEW EXAM STUFF

    /**
     * Add modified copies values from one list to another but first passing it through a modifier function
     *
     * @param <K>      typeof other collection
     * @param c1       other collection copied from
     * @param modifier the modifier
     */
    default <K> void addModified(Collection<K> c1, IModifier<T, K> modifier) {
        c1.forEach(e -> this.add(modifier.modify(e)));
    }

    /**
     * Create elements getter elements getter.
     *
     * @return the elements getter
     */
    ElementsGetter<T> createElementsGetter();

    /**
     * Add all satisfying.
     *
     * @param col    the col
     * @param tester the tester
     */
    default void addAllSatisfying(Collection<T> col, Tester<T> tester) {
        ElementsGetter<T> eg = col.createElementsGetter();
        while (eg.hasNextElement()) {
            T tmp = eg.getNextElement();
            if (tester.test(tmp)) {
                this.add(tmp);
            }
        }
    }

    /**
     * Size of collection
     *
     * @return size of collection
     */
    int size();

    /**
     * Checks if collection is empty
     *
     * @return if it's empty
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes all elements from a collection
     */
    void clear();

    /**
     * Adds new element to the collection
     *
     * @param value new element
     */
    void add(T value);

    /**
     * Checks if an element is inside a collection
     *
     * @param value element to check
     * @return if is in collection
     */
    boolean contains(Object value);

    /**
     * Removes element from collection
     *
     * @param value element to remove
     * @return if removal was successful
     */
    boolean remove(T value);

    /**
     * Conversion method that returns an array of Objects
     *
     * @return array representation
     */
    T[] toArray();

    /**
     * Method for iterating over collections items with a Processor instance
     *
     * @param processor the processor
     */
    void forEach(Processor<T> processor);

    /**
     * Method for adding all elements from the <code>other</code> collection into this collection
     *
     * @param other the other
     */
    default void addAll(Collection<T> other) {
        class AddItemsProcessor implements Processor<T> {
            @Override
            public void process(T value) {
                Collection.this.add(value);
            }
        }

        other.forEach(new AddItemsProcessor());
    }
}
