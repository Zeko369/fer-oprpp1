package hr.fer.oprpp1.hw04.db;

/**
 * The interface Field value getter.
 *
 * @author franzekan
 */
public interface IFieldValueGetter {
    /**
     * Get string value from a student record
     *
     * @param record the record
     * @return the string
     */
    String get(StudentRecord record);
}
