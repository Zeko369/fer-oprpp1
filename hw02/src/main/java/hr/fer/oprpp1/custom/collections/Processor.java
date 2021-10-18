package hr.fer.oprpp1.custom.collections;

/**
 * Base class for describing creation of executable functions that can be used in iteration over objects
 *
 * @author franzekan
 * @version 1.0
 */
public interface Processor {
    /**
     * Method called for each item
     *
     * @param value the value
     */
    void process(Object value);
}
