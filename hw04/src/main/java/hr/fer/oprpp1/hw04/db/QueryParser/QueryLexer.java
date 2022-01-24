package hr.fer.oprpp1.hw04.db.QueryParser;

import java.util.Arrays;
import java.util.List;

/**
 * Lexer for our simple query language
 *
 * @author franzekan
 */
public class QueryLexer {
    /**
     * Array representation of input query
     */
    private final char[] query;

    /**
     * Last token returned by nextToken()
     */
    private QueryToken token = null;

    /**
     * Current index in query
     */
    private int index;

    /**
     * Instantiates a new Query lexer.
     *
     * @param query the query
     */
    public QueryLexer(String query) {
        this.query = query.toCharArray();
    }

    private static final List<String> operatorKeyword = List.of(new String[]{
            ">", ">=", "=", "<=", "<", "!=", "LIKE"
    });

    private static final List<String> logicalOperatorKeyword = List.of(new String[]{
            "AND"
    });

    // TODO: remove stuff from exam
    private static final List<String> optionKeywords = List.of(new String[]{
            "with-statistics"
    });

    private static final String ORDER_BY_KEYWORD = "orderby";

    private QueryToken setToken(QueryTokenType type, String value) {
        this.token = new QueryToken(type, value);
        return this.token;
    }

    private char getCurrent() {
        if (this.index >= this.query.length) {
            return '\0';
        }

        return this.query[this.index];
    }

    private char getCurrentAndMove() {
        return this.query[this.index++];
    }

    private boolean currentIsWhitespace() {
        return Character.isWhitespace(this.getCurrent());
    }

    private void skipSpace() {
        if (!this.currentIsWhitespace()) return;
        while (this.currentIsWhitespace()) {
            this.index++;
        }
    }

    private boolean checkSequence(String seq) {
        if (this.index + seq.length() > this.query.length) return false;

        for (int i = 0; i < seq.length(); i++) {
            char c = Character.toUpperCase(this.query[this.index + i]);
            if (seq.toUpperCase().charAt(i) != c) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets last parsed token.
     *
     * @return the token
     */
    public QueryToken getToken() {
        return this.token;
    }

    /**
     * Gets next token.
     *
     * @return the next token
     */
    public QueryToken getNextToken() {
        this.skipSpace();
        if (this.index >= this.query.length) {
            return this.setToken(QueryTokenType.EOF, null);
        }

        if (this.getToken() != null && this.getToken().getType() == QueryTokenType.ORDER_BY) {
            StringBuilder sb = new StringBuilder();
            for (int i = this.index; i < this.query.length; i++) {
                sb.append(this.query[i]);
            }

            this.index = this.query.length;

            return this.setToken(QueryTokenType.ORDER_BY_LIST, sb.toString());
        }

        if (this.checkSequence(ORDER_BY_KEYWORD)) {
            this.index += ORDER_BY_KEYWORD.length();
            return this.setToken(QueryTokenType.ORDER_BY, "orderBy");
        }

        // OPTION
        for (String option : optionKeywords) {
            if (this.checkSequence(option)) {
                this.index += option.length();
                return this.setToken(QueryTokenType.OPTION, option);
            }
        }

        // VALUE
        if (this.getCurrent() == '"') {
            StringBuilder sb = new StringBuilder();

            this.index++;
            while (this.getCurrent() != '"') {
                sb.append(this.getCurrentAndMove());
            }
            this.index++;

            return this.setToken(QueryTokenType.VALUE, sb.toString());
        }

        // OPERATOR
        for (String operator : operatorKeyword) {
            if (this.checkSequence(operator)) {
                this.index += operator.length();
                return this.setToken(QueryTokenType.OPERATOR, operator);
            }
        }

        // LOGICAL OPERATOR
        for (String logicalOperator : logicalOperatorKeyword) {
            if (this.checkSequence(logicalOperator)) {
                this.index += logicalOperator.length();
                return this.setToken(QueryTokenType.LOGICAL_OPERATOR, logicalOperator);
            }
        }

        // COLUMN
        StringBuilder sb = new StringBuilder();
        while (Character.isLetter(this.getCurrent())) {
            sb.append(this.getCurrentAndMove());
        }

        return this.setToken(QueryTokenType.COLUMN, sb.toString());
    }
}
