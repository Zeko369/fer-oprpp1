package hr.fer.oprpp1.hw04.db;

/**
 * Expression throws if wrong values are passed into a comparison operator. (Currently only in LIKE)
 *
 * @author franzekan
 */
public class ComparisonOperatorException extends RuntimeException {
    /**
     * Instantiates a new Comparison operator exception.
     *
     * @param message the message
     */
    public ComparisonOperatorException(String message) {
        super(message);
    }
}
