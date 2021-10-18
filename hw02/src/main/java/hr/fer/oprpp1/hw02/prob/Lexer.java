package hr.fer.oprpp1.hw02.prob;

import java.util.Objects;

public class Lexer {
    private final char[] data;
    private Token token;
    private int currentIndex;
    private LexerState state;

    public Lexer(String text) {
        this.currentIndex = 0;
        this.state = LexerState.BASIC;

        this.data = text.toCharArray();
    }

    public void setState(LexerState state) {
        this.state = Objects.requireNonNull(state);
    }

    private boolean isEnd() {
        return this.currentIndex == this.data.length;
    }

    private char getCurrent() {
        return this.data[this.currentIndex];
    }

    private char getCurrentNext() {
        return this.data[this.currentIndex++];
    }

    private Token setToken(Token t) {
        this.token = t;
        return t;
    }

    // generira i vraća sljedeći token
// baca LexerException ako dođe do pogreške
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
                tmp.append(this.getCurrentNext());
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

                tmp.append(this.getCurrentNext());
            }

            while (!this.isEnd() && (Character.isLetter(this.getCurrent()) || this.getCurrent() == '\\')) {
                if (this.getCurrent() == '\\') {
                    this.currentIndex++;
                }

                tmp.append(this.getCurrentNext());
            }

            return this.setToken(new Token(TokenType.WORD, tmp.toString()));
        } else if (Character.isDigit(this.getCurrent())) {
            while (!this.isEnd() && Character.isDigit(this.getCurrent())) {
                tmp.append(this.getCurrentNext());
            }

            try {
                return this.setToken(new Token(TokenType.NUMBER, Long.parseLong(tmp.toString())));
            } catch (NumberFormatException ex) {
                throw new LexerException("Number not ok");
            }
        } else {
            return this.setToken(new Token(TokenType.SYMBOL, this.getCurrentNext()));
        }
    }

    public Token getToken() {
        return this.token;
    }
}
