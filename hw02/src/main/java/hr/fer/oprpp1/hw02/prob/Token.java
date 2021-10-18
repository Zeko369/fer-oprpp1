package hr.fer.oprpp1.hw02.prob;

public class Token {
    private final TokenType type;
    private final Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public TokenType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.type, this.value);
    }
}

//public record Token(TokenType type, Object value) {
//
//    public Object getValue() {
//        return this.value;
//    }
//
//    public TokenType getType() {
//        return this.type;
//    }
//}
