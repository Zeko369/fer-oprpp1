package hr.fer.oprpp1.hw02.prob;

import java.util.Objects;

/**
 * Simple example lexer
 *
 * @author franzekan
 */
public class Lexer {
    /**
     * input string
     */
    private final char[] data;

    /**
     * Last found token
     */
    private Token token;

    /**
     * Index in input string
     */
    private int currentIndex;

    /**
     * Current lexer state
     */
    private LexerState state;

    /**
     * Instantiates a new Lexer.
     *
     * @param text the text
     */
    public Lexer(String text) {
        this.currentIndex = 0;
        this.state = LexerState.BASIC;

        this.data = text.toCharArray();
    }

    /**
     * state setter
     *
     * @param state the state
     */
    public void setState(LexerState state) {
        this.state = Objects.requireNonNull(state);
    }

    private boolean isEnd() {
        return this.currentIndex == this.data.length;
    }

    private char getCurrent() {
        return this.data[this.currentIndex];
    }

    private char getCurrentAndMode() {
        return this.data[this.currentIndex++];
    }

    private Token setToken(Token t) {
        this.token = t;
        return t;
    }

    /**
     * Parses and returns the next token
     *
     * @return the token
     * @throws LexerException if there is an error with the format or if it's out of range
     */
    public Token nextToken() {
        if (this.currentIndex > this.data.length) {
            throw new LexerException();
        }

        while (!this.isEnd() && Character.isWhitespace(this.getCurrent())) {
            this.currentIndex++;
        }

        if (this.isEnd()) {
            this.currentIndex++;
            return this.setToken(new Token(TokenType.EOF, null));
        }

        StringBuilder tmp = new StringBuilder();

        // Handle EXTENDED
        if (this.state == LexerState.EXTENDED) {
            if (this.getCurrent() == '#') {
                this.currentIndex++;
                return this.setToken(new Token(TokenType.SYMBOL, '#'));
            }

            while (!this.isEnd() && !Character.isWhitespace(this.getCurrent()) && this.getCurrent() != '#') {
                tmp.append(this.getCurrentAndMode());
            }

            return this.setToken(new Token(TokenType.WORD, tmp.toString()));
        }

        // Handle SIMPLE
        if (this.getCurrent() == '\\' || Character.isLetter(this.getCurrent())) {
            if (this.getCurrent() == '\\') {
                this.currentIndex++;

                if (this.isEnd() || !Character.isDigit(this.getCurrent())) {
                    throw new LexerException("Invalid escape sequence");
                }

                tmp.append(this.getCurrentAndMode());
            }

            while (!this.isEnd() && (Character.isLetter(this.getCurrent()) || this.getCurrent() == '\\')) {
                if (this.getCurrent() == '\\') {
                    this.currentIndex++;
                }

                tmp.append(this.getCurrentAndMode());
            }

            return this.setToken(new Token(TokenType.WORD, tmp.toString()));
        } else if (Character.isDigit(this.getCurrent())) {
            while (!this.isEnd() && Character.isDigit(this.getCurrent())) {
                tmp.append(this.getCurrentAndMode());
            }

            try {
                return this.setToken(new Token(TokenType.NUMBER, Long.parseLong(tmp.toString())));
            } catch (NumberFormatException ex) {
                throw new LexerException("Number not ok");
            }
        } else {
            return this.setToken(new Token(TokenType.SYMBOL, this.getCurrentAndMode()));
        }
    }

    /**
     * Gets the last parsed token
     *
     * @return the token
     */
    public Token getToken() {
        return this.token;
    }
}
