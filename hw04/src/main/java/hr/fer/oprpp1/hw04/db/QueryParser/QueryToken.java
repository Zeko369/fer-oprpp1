package hr.fer.oprpp1.hw04.db.QueryParser;

/**
 * The type Query token.
 *
 * @author franzekan
 */
public class QueryToken {
    private final QueryTokenType type;
    private final String value;

    /**
     * Instantiates a new Query token.
     *
     * @param type  the type
     * @param value the value
     */
    public QueryToken(QueryTokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Simple check to check if the token is an end of query.
     *
     * @return the boolean
     */
    public boolean isEOF()  {
        return type == QueryTokenType.EOF;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public QueryTokenType getType() {
        return type;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + " " + value;
    }
}
