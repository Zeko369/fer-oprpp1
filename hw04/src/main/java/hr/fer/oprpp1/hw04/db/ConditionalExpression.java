package hr.fer.oprpp1.hw04.db;

public class ConditionalExpression {
    private final IFieldValueGetter fieldGetter;
    private final String stringLiteral;
    private final IComparisonOperator comparisonOperator;

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
