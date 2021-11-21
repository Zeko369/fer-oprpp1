package hr.fer.oprpp1.custom.collections;

/**
 * The interface Elements getter.
 *
 * @param <T> the type parameter
 * @author franzekan
 */
public interface ElementsGetter<T> {
    /**
     * Gets next element.
     *
     * @return the next element
     */
    T getNextElement();

    /**
     * Has next element boolean.
     *
     * @return the boolean
     */
    boolean hasNextElement();

    /**
     * Process remaining.
     *
     * @param p the p
     */
    default void processRemaining(Processor<T> p) {
        while (this.hasNextElement()) {
            p.process(this.getNextElement());
        }
    }
}
