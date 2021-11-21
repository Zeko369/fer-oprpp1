package hr.fer.oprpp1.hw04.db;

import java.util.function.Predicate;

public class ConditionalExpression {
    private final IFieldValueGetter fieldGetter;
    private final String stringLiteral;
    private final IComparisonOperator comparisonOperator;

    public static Predicate<ConditionalExpression> expressionIsEqualJMBAG = (ce) -> ce.getComparisonOperator().equals(ComparisonOperators.EQUALS)
            && ce.getFieldGetter().equals(FieldValueGetters.JMBAG);

    public ConditionalExpression(IFieldValueGetter fieldGetter, String stringLiteral, IComparisonOperator operator) {
        this.fieldGetter = fieldGetter;
        this.stringLiteral = stringLiteral;
        this.comparisonOperator = operator;
    }

    public boolean check(StudentRecord sr) {
        return comparisonOperator.satisfied(fieldGetter.get(sr), stringLiteral);
    }

    public IComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public IFieldValueGetter getFieldGetter() {
        return fieldGetter;
    }

    public String getStringLiteral() {
        return stringLiteral;
    }
}
