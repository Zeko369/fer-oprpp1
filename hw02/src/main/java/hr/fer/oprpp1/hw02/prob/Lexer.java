package hr.fer.oprpp1.hw02.prob;

public class Lexer {
    private final char[] data;
    private Token token; // trenutni token
    private int currentIndex; // indeks prvog neobrađenog znaka

    public Lexer(String text) {
        this.currentIndex = 0;
        this.data = text.toCharArray();
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

    // generira i vraća sljedeći token
// baca LexerException ako dođe do pogreške
    public Token nextToken() {
        if (this.currentIndex > this.data.length) {
            return null;
        }

        if (this.isEnd()) {
            this.currentIndex++;
            return new Token(TokenType.EOF, null);
        }

        while(Character.isWhitespace(this.getCurrent())) {
            this.currentIndex++;
        }

        StringBuilder tmp = new StringBuilder();
        if (Character.isLetter(this.getCurrent())) {
            while (!this.isEnd() && Character.isLetter(this.getCurrent())) {
                tmp.append(this.getCurrentNext());
            }

            return new Token(TokenType.WORD, tmp);
        } else if (Character.isDigit(this.getCurrent())) {
            while (!this.isEnd() && Character.isDigit(this.getCurrent())) {
                tmp.append(this.getCurrentNext());
            }

            return new Token(TokenType.NUMBER, tmp);
        } else {
            return new Token(TokenType.SYMBOL, this.getCurrentNext());
        }
    }

    public Token getToken() {
        return this.token;
    }
}
