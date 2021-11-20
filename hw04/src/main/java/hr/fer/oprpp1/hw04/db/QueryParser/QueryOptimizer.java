package hr.fer.oprpp1.hw04.db.QueryParser;

import hr.fer.oprpp1.hw04.db.*;

import java.util.*;

public class QueryOptimizer {
    private final List<ConditionalExpression> expressions;

    public QueryOptimizer(List<ConditionalExpression> expressions) {
        this.expressions = expressions;
    }

    public List<ConditionalExpression> getOptimizedQuery() {
        List<ConditionalExpression> ret = this.checkForEqualsDiffValue();
        if (ret != null) return ret;

        return this.expressions;
    }

    private List<ConditionalExpression> checkForEqualsDiffValue() {
        Map<Integer, Set<String>> repetitions = new HashMap<>();

        // Optimize for same column with different values and equals
        for (ConditionalExpression expression : expressions) {
            if (!expression.getComparisonOperator().equals(ComparisonOperators.EQUALS)) {
                continue;
            }

            if (!repetitions.containsKey(expression.getFieldGetter().hashCode())) {
                repetitions.put(expression.getFieldGetter().hashCode(), new HashSet<>());
            }

            repetitions.get(expression.getFieldGetter().hashCode()).add(expression.getStringLiteral());
        }

        for (Map.Entry<Integer, Set<String>> entry : repetitions.entrySet()) {
            if (entry.getValue().size() > 1) {
                return List.of(new ConditionalExpression(FieldValueGetters.JMBAG, "", ComparisonOperators.NEVER));
            }
        }

        return null;
    }
}
