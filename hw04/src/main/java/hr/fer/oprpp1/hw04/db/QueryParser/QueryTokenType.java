package hr.fer.oprpp1.hw04.db.QueryParser;

/**
 * The enum Query token type.
 *
 * @author franzekan
 */
public enum QueryTokenType {
    /**
     * Column token type.
     */
    COLUMN,

    /**
     * Operator token type. (=, <, >, <=, >=, !=)
     */
    OPERATOR,

    /**
     * Logical operator token type. (AND)
     */
    LOGICAL_OPERATOR,

    /**
     * Value query token type.
     */
    VALUE,

    /**
     * Eof query token type.
     */
    EOF,

    /**
     * Query option token type
     */
    OPTION,

    // TODO: Maybe add later for finalGrade
    // INTEGER, and convert VALUE into STRING
}
