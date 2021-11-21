package hr.fer.oprpp1.hw04.db;

/**
 * The interface Comparison operator.
 *
 * @author franzekan
 */
public interface IComparisonOperator {
    /**
     * Satisfied boolean.
     *
     * @param value1 the value 1
     * @param value2 the value 2
     * @return the boolean
     * @throws ComparisonOperatorException the comparison operator exception
     */
    boolean satisfied(String value1, String value2) throws ComparisonOperatorException;
}
