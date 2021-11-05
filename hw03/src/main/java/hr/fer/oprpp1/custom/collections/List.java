package hr.fer.oprpp1.custom.collections;

/**
 * The interface List.
 *
 * @param <T> the type parameter
 * @author franzekan
 */
public interface List<T> extends Collection<T> {
    /**
     * Get t.
     *
     * @param index the index
     * @return the t
     */
    T get(int index);

    /**
     * Insert.
     *
     * @param value    the value
     * @param position the position
     */
    void insert(T value, int position);

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
