package hr.fer.oprpp1.hw04.db.QueryParser;

import hr.fer.oprpp1.hw04.db.ComparisonOperators;
import hr.fer.oprpp1.hw04.db.ConditionalExpression;
import hr.fer.oprpp1.hw04.db.FieldValueGetters;
import hr.fer.oprpp1.hw04.db.IFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Query parser.
 *
 * @author franzekan
 */
public class QueryParser {
    private List<ConditionalExpression> query = new ArrayList<>();

    /**
     * ConditionalExpression with JMBAG=value if found, or null
     */
    private final ConditionalExpression direct;

    /**
     * Instantiates a new Query parser.
     *
     * @param query the query
     * @throws QueryParserException if query is invalid
     */
    public QueryParser(String query) throws QueryParserException {
        try {
            this.parse(query);
        } catch (RuntimeException e) {
            throw new QueryParserException("Error parsing query");
        }

        QueryOptimizer opt = new QueryOptimizer(this.query);
        this.query = opt.getOptimizedQuery();
        this.direct = this.findDirectQuery();
    }

    private ConditionalExpression findDirectQuery() {
        List<ConditionalExpression> tmp = this.query.stream()
                .filter(ConditionalExpression.expressionIsEqualJMBAG)
                .toList();

        Set<String> values = tmp.stream().map(ConditionalExpression::getStringLiteral).collect(Collectors.toSet());
        if (values.size() == 1) {
            return tmp.get(0);
        }

        return null;
    }

    private void parse(String query) throws QueryParserException {
        QueryLexer lexer = new QueryLexer(query);
        QueryToken token = lexer.getNextToken();

        while (!token.isEOF()) {
            if (token.getType() == QueryTokenType.LOGICAL_OPERATOR) {
                if (this.query.size() == 0) {
                    throw new QueryParserException("Unexpected logical operator");
                }

                token = lexer.getNextToken();
                continue;
            }

            if (token.getType() != QueryTokenType.COLUMN) {
                throw new QueryParserException("Unexpected type");
            }


            QueryToken operator = lexer.getNextToken();
            if (operator.getType() != QueryTokenType.OPERATOR) {
                throw new QueryParserException("Wrong order, OPERATOR expected");
            }

            QueryToken value = lexer.getNextToken();
            if (value.getType() != QueryTokenType.VALUE) {
                throw new QueryParserException("Wrong order, VALUE expected");
            }

            this.query.add(new ConditionalExpression(
                    FieldValueGetters.getByName(token.getValue()),
                    value.getValue(),
                    ComparisonOperators.getByName(operator.getValue()))
            );
            token = lexer.getNextToken();
        }
    }

    /**
     * Checks if is never query (conditions short circuit)
     *
     * @return the boolean
     */
    public boolean isNever() {
        return this.query.size() == 1 && this.query.get(0).getComparisonOperator().equals(ComparisonOperators.NEVER);
    }

    /**
     * Is direct query boolean.
     *
     * @return the boolean
     */
    public boolean isDirectQuery() {
        return this.direct != null;
    }

    /**
     * Gets queried jmbag.
     *
     * @return the queried jmbag
     * @throws IllegalStateException if isDirectQuery() == false
     */
    public String getQueriedJMBAG() throws IllegalStateException {
        if (this.isDirectQuery()) {
            return this.direct.getStringLiteral();
        }

        throw new IllegalStateException("Not a direct query");
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public List<ConditionalExpression> getQuery() {
        return this.query;
    }

    /**
     * Gets filter.
     *
     * @return the filter
     */
    public IFilter getFilter() {
        return studentRecord -> {
            for (ConditionalExpression expression : query) {
                if (!expression.check(studentRecord)) {
                    return false;
                }
            }

            return true;
        };
    }
}