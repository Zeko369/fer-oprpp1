package hr.fer.oprpp1.custom.scripting.lexer;

public class Token {
    private final TokenType type;
    private final Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case EOF -> "(EOF)";
            case TAG_OPEN, TAG_CLOSE -> String.format("(%s)", this.type);
            default -> String.format("(%s, '%s')", this.type, this.value.toString().replace("\n", "\\n"));
        };

    }
}
