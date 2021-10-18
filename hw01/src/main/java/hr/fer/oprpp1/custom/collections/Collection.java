package hr.fer.oprpp1.custom.collections;

/**
 * "abstract" base class for every custom Object collection we make
 *
 * @author franzekan
 * @version 1.0
 */
public class Collection {
    /**
     * Size of collection
     *
     * @return size of collection
     */
    int size() {
        return 0;
    }

    /**
     * Checks if collection is empty
     *
     * @return if it's empty
     */
    boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes all elements from a collection
     */
    void clear() {
    }

    /**
     * Adds new element to the collection
     *
     * @param value new element
     */
    void add(Object value) {
    }

    /**
     * Checks if an element is inside a collection
     *
     * @param value element to check
     * @return if is in collection
     */
    boolean contains(Object value) {
        return false;
    }

    /**
     * Removes element from collection
     *
     * @param value element to remove
     * @return if removal was successful
     */
    boolean remove(Object value) {
        return false;
    }

    /**
     * Conversion method that returns an array of Objects
     *
     * @return array representation
     */
    Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method for iterating over collections items with a Processor instance
     *
     * @param processor the processor
     */
    void forEach(Processor processor) {
    }

    /**
     * Method for adding all elements from the <code>other</code> collection into this collection
     *
     * @param other the other
     */
    void addAll(Collection other) {
        class AddItemsProcessor extends Processor {
            @Override
            public void process(Object value) {
                Collection.this.add(value);
            }
        }

        other.forEach(new AddItemsProcessor());
    }
}
