package hr.fer.oprpp1.hw04.db.QueryParser;

import hr.fer.oprpp1.hw04.db.*;

import java.util.*;

/**
 * Simple query optimizer, currently only optimizing
 * - multiple JMABG= with different values -> NEVER
 *
 * @author franzekan
 */
public class QueryOptimizer {
    private final List<ConditionalExpression> expressions;

    /**
     * Instantiates a new Query optimizer.
     *
     * @param expressions the expressions
     */
    public QueryOptimizer(List<ConditionalExpression> expressions) {
        this.expressions = expressions;
    }

    /**
     * Gets optimized query.
     *
     * @return the optimized query
     */
    public List<ConditionalExpression> getOptimizedQuery() {
        List<ConditionalExpression> ret = this.checkForEqualsDiffValue();
        if (ret != null) return ret;

        return this.expressions;
    }

    /**
     * Optimize for same column equals with different values
     *
     * @return ConditionalExpression(NEVER) if found multiple JMABG= with different values, otherwise null
     */
    private List<ConditionalExpression> checkForEqualsDiffValue() {
        Map<Integer, Set<String>> repetitions = new HashMap<>();

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
