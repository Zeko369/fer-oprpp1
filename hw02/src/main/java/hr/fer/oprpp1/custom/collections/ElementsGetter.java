package hr.fer.oprpp1.custom.collections;

/**
 * The interface Elements getter.
 *
 * @author franzekan
 */
public interface ElementsGetter {
    /**
     * Gets next element.
     *
     * @return the next element
     */
    Object getNextElement();

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
    default void processRemaining(Processor p) {
        while (this.hasNextElement()) {
            p.process(this.getNextElement());
        }
    }
}
