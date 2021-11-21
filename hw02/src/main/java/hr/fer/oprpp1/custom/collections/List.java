package hr.fer.oprpp1.custom.collections;

/**
 * The interface List.
 *
 * @author franzekan
 */
public interface List extends Collection {
    /**
     * Get object.
     *
     * @param index the index
     * @return the object
     */
    Object get(int index);

    /**
     * Insert.
     *
     * @param value    the value
     * @param position the position
     */
    void insert(Object value, int position);

    /**
     * Index of int.
     *
     * @param value the value
     * @return the int
     */
    int indexOf(Object value);

    /**
     * Remove.
     *
     * @param index the index
     */
    void remove(int index);
}
