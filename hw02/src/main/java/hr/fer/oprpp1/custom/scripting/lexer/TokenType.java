package hr.fer.oprpp1.custom.scripting.lexer;

public enum TokenType {
    TEXT,
    TAG_OPEN,
    TAG_CLOSE,
    SYMBOL,
    OPERATOR,
    FUNCTION,
    COMMAND,
    STRING,
    VARIABLE,
    INTEGER,
    DOUBLE,
    EOF
}
