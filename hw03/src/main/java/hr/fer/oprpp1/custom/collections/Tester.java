package hr.fer.oprpp1.custom.collections;

/**
 * The interface Tester.
 *
 * @param <T> the type parameter
 * @author franzekan
 */
public interface Tester<T> {
    /**
     * Test boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    boolean test(T obj);
}
