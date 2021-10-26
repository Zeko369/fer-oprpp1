package hr.fer.oprpp1.custom.scripting.lexer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {
    @Test
    public void throwsForNullInputTest() {
        assertThrows(NullPointerException.class, () -> new Lexer(null));
    }

    @Test
    public void returnsEOFTokenTest() {
        Lexer l = new Lexer("");
        checkTokenStream(l, new Token[]{new Token(TokenType.EOF, null)});
    }

    @Test
    public void simpleNoTagsTest() {
        Lexer l = new Lexer("Foo Bar");
        checkTokenStream(l, new Token[]{
                new Token(TokenType.TEXT, "Foo Bar"),
                new Token(TokenType.EOF, null)
        });
    }

    @Test
    public void simpleEchoTagTest() {
        Lexer l = new Lexer("{$= 123 + 10.123 @func variable \"string\" $}");
        checkTokenStream(l, new Token[]{
                new Token(TokenType.TAG_OPEN, "{$"),
                new Token(TokenType.SYMBOL, '='),
                new Token(TokenType.INTEGER, 123),
                new Token(TokenType.OPERATOR, "+"),
                new Token(TokenType.DOUBLE, 10.123),
                new Token(TokenType.FUNCTION, "func"),
                new Token(TokenType.VARIABLE, "variable"),
                new Token(TokenType.STRING, "string"),
                new Token(TokenType.TAG_CLOSE, "$}"),
                new Token(TokenType.EOF, null)
        });
    }

    @Test
    public void simpleForLoopTest() {
        Lexer l = new Lexer("{$ FoR i 0 10 1 $} Foo Bar {$ END $}");
        checkTokenStream(l, new Token[]{
                new Token(TokenType.TAG_OPEN, "{$"),
                new Token(TokenType.COMMAND, "FOR"),
                new Token(TokenType.VARIABLE, "i"),
                new Token(TokenType.INTEGER, 0),
                new Token(TokenType.INTEGER, 10),
                new Token(TokenType.INTEGER, 1),
                new Token(TokenType.TAG_CLOSE, "$}"),
                new Token(TokenType.TEXT, " Foo Bar "),
                new Token(TokenType.TAG_OPEN, "{$"),
                new Token(TokenType.COMMAND, "END"),
                new Token(TokenType.TAG_CLOSE, "$}"),
                new Token(TokenType.EOF, null)
        });
    }

    @Test
    public void eatSpaceTest() {
        Lexer l = new Lexer("{$   =    123     i $}");
        checkTokenStream(l, new Token[]{
                new Token(TokenType.TAG_OPEN, "{$"),
                new Token(TokenType.SYMBOL, '='),
                new Token(TokenType.INTEGER, 123),
                new Token(TokenType.VARIABLE, "i"),
                new Token(TokenType.TAG_CLOSE, "$}"),
                new Token(TokenType.EOF, null)
        });
    }

    @Test
    public void stateChangeTest() {
        Lexer l = new Lexer("Foo {$= 10 $}");

        assertEquals(LexerState.NORMAL, l.getState());
        checkTokenStream(l, new Token[]{
                new Token(TokenType.TEXT, "Foo "),
                new Token(TokenType.TAG_OPEN, "{$"),
        });


        assertEquals(LexerState.TAG, l.getState());
        checkTokenStream(l, new Token[]{
                new Token(TokenType.SYMBOL, '='),
                new Token(TokenType.INTEGER, 10),
                new Token(TokenType.TAG_CLOSE, "$}"),
                new Token(TokenType.EOF, null)
        });

        assertEquals(LexerState.NORMAL, l.getState());
    }

    @Test
    public void returnsLastAfterEndTest() {
        Lexer l = new Lexer("Foo");

        checkTokenStream(l, new Token[]{
                new Token(TokenType.TEXT, "Foo"),
                new Token(TokenType.EOF, null),
        });

        assertEquals(l.getNextToken(), l.getCurrentToken());
    }

    @Test
    public void throwsErrorForInvalidNumberTest() {
        Lexer l = new Lexer("{$= 100000000000000000000000 $}");
        l.getNextToken();
        l.getNextToken();

        assertThrows(LexerException.class, l::getNextToken);
    }

    @Test
    public void throwsErrorForInvalidVariableNameTest() {
        Lexer l = new Lexer("{$= _asd $}");
        l.getNextToken();
        l.getNextToken();

        assertThrows(LexerException.class, l::getNextToken);
    }

    @Test
    public void throwsErrorForBackslashNInTextTest() {
        Lexer l = new Lexer("Foo \\n asd");
        assertThrows(LexerException.class, l::getNextToken);
    }

    private static void checkTokenStream(Lexer lexer, Token[] correctData) {
        int counter = 0;
        for (Token expected : correctData) {
            Token actual = lexer.getNextToken();
            String msg = "Checking token " + counter + ":";

            assertEquals(expected.getType(), actual.getType(), msg);
            assertEquals(expected.getValue(), actual.getValue(), msg);
            counter++;
        }
    }
}
