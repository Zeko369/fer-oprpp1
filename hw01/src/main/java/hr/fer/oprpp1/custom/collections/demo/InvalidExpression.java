package hr.fer.oprpp1.custom.collections.demo;

/**
 * Exception for when the expression input isn't valid
 *
 * @author franzekan
 * @version 1.0
 */
public class InvalidExpression extends RuntimeException {
    /**
     * Instantiates a new Invalid expression.
     *
     * @param problem the problem
     */
    public InvalidExpression(String problem) {
        super(problem);
    }
}
