package hr.fer.oprpp1.hw04.db.QueryParser;

/**
 * Query parser exception thrown when there is a problem with the query during parsing or lexing
 *
 * @author franzekan
 */
public class QueryParserException extends RuntimeException {
    /**
     * Instantiates a new Query parser exception.
     *
     * @param message the message
     */
    public QueryParserException(String message) {
        super(message);
    }
}
