package hr.fer.oprpp1.hw02.prob;

/**
 * The type Token.
 *
 * @author franzekan
 */
public class Token {
    private final TokenType type;
    private final Object value;

    /**
     * Instantiates a new Token.
     *
     * @param type  the type
     * @param value the value
     */
    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public TokenType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("(%s, |%s|)", this.type, this.value);
    }
}
