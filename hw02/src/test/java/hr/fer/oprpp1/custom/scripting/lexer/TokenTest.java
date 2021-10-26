package hr.fer.oprpp1.custom.scripting.lexer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {
    @Test
    public void toStringEofTest() {
        Token t = new Token(TokenType.EOF, null);
        assertEquals("(EOF)", t.toString());
    }

    @Test
    public void toStringTagOpenCloseTest() {
        Token t1 = new Token(TokenType.TAG_OPEN, null);
        Token t2 = new Token(TokenType.TAG_CLOSE, null);

        assertEquals("(TAG_OPEN)", t1.toString());
        assertEquals("(TAG_CLOSE)", t2.toString());
    }

    @Test
    public void toStringOtherTest() {
        Token t = new Token(TokenType.FUNCTION, "foo");
        assertEquals("(FUNCTION, 'foo')", t.toString());
    }
}
