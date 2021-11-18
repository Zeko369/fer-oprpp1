package hr.fer.oprpp1.hw04.db.QueryParser;

import hr.fer.oprpp1.hw04.db.ComparisonOperators;
import hr.fer.oprpp1.hw04.db.ConditionalExpression;
import hr.fer.oprpp1.hw04.db.FieldValueGetters;

import java.util.ArrayList;
import java.util.List;

public class QueryParser {
    private final List<ConditionalExpression> query = new ArrayList<>();

    public QueryParser(String query) {
        this.parse(query);
    }

    private void parse(String query) {
        QueryLexer lexer = new QueryLexer(query);
        QueryToken token = lexer.getNextToken();


        while (!token.isEOF()) {
            if (token.getType() == QueryTokenType.LOGICAL_OPERATOR) {
                if (this.query.size() == 0) {
                    throw new IllegalStateException();
                }

                token = lexer.getNextToken();
                continue;
            }

            if (token.getType() != QueryTokenType.COLUMN) {
                throw new IllegalStateException();
            }


            QueryToken operator = lexer.getNextToken();
            if (operator.getType() != QueryTokenType.OPERATOR) {
                throw new IllegalStateException("Wrong order, OPERATOR expected");
            }

            QueryToken value = lexer.getNextToken();
            if (value.getType() != QueryTokenType.VALUE) {
                throw new IllegalStateException("Wrong order, VALUE expected");
            }

            this.query.add(new ConditionalExpression(
                    FieldValueGetters.getByName(token.getValue()),
                    value.getValue(),
                    ComparisonOperators.getByName(operator.getValue()))
            );
            token = lexer.getNextToken();
        }
    }

    public boolean isDirectQuery() {
        if (this.query.size() != 1) {
            return false;
        }

        ConditionalExpression expression = this.query.get(0);
        return expression.getFieldGetter().equals(FieldValueGetters.JMBAG) && expression.getComparisonOperator().equals(ComparisonOperators.EQUALS);
    }

    public String getQueriedJMBAG() {
        if (this.isDirectQuery()) {
            return this.query.get(0).getStringLiteral();
        }

        throw new IllegalStateException();
    }

    public List<ConditionalExpression> getQuery() {
        return this.query;
    }
}
