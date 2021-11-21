package hr.fer.oprpp1.hw04.db;

/**
 * The interface Filter.
 *
 * @author franzekan
 */
public interface IFilter {
    /**
     * Accepts boolean.
     *
     * @param record the record
     * @return the boolean
     */
    boolean accepts(StudentRecord record);
}
