package hr.fer.oprpp1.hw04.db;

import java.util.function.Predicate;

/**
 * The type Conditional expression.
 *
 * @author franzekan
 */
public class ConditionalExpression {
    private final IFieldValueGetter fieldGetter;
    private final String stringLiteral;
    private final IComparisonOperator comparisonOperator;

    /**
     * The Expression is equal jmbag.
     */
    public static Predicate<ConditionalExpression> expressionIsEqualJMBAG = (ce) -> ce.getComparisonOperator().equals(ComparisonOperators.EQUALS)
            && ce.getFieldGetter().equals(FieldValueGetters.JMBAG);

    /**
     * Instantiates a new Conditional expression.
     *
     * @param fieldGetter   the field getter
     * @param stringLiteral the string literal
     * @param operator      the operator
     */
    public ConditionalExpression(IFieldValueGetter fieldGetter, String stringLiteral, IComparisonOperator operator) {
        this.fieldGetter = fieldGetter;
        this.stringLiteral = stringLiteral;
        this.comparisonOperator = operator;
    }

    /**
     * Check if the StudentRecord matches the condition.
     *
     * @param sr the sr
     * @return the boolean
     */
    public boolean check(StudentRecord sr) {
        return comparisonOperator.satisfied(fieldGetter.get(sr), stringLiteral);
    }

    /**
     * Gets comparison operator.
     *
     * @return the comparison operator
     */
    public IComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    /**
     * Gets field getter.
     *
     * @return the field getter
     */
    public IFieldValueGetter getFieldGetter() {
        return fieldGetter;
    }

    /**
     * Gets string literal.
     *
     * @return the string literal
     */
    public String getStringLiteral() {
        return stringLiteral;
    }
}
