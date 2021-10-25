package hr.fer.oprpp1.custom.scripting.lexer;

import hr.fer.oprpp1.custom.scripting.lexer.demo.Loader;

public class Lexer {
    public static void main(String[] args) {
        String code = Loader.loadCode("./demos/code_escape_quote.txt");
        System.out.println(code);

        int i = 0;
        Token t;
        Lexer l = new Lexer(code);

        do {
            LexerState s = l.getState();
            t = l.getNextToken();
            System.out.printf("%s %s%n", s == LexerState.NORMAL ? "NOR" : "TAG", t);
            i++;
        } while (t.getType() != TokenType.EOF && i < 50);
    }

    private int index = 0;
    private final char[] data;
    private LexerState state = LexerState.NORMAL;
    private Token token = null;

    public Lexer(String str) {
        this.data = str.toCharArray();
    }

    private boolean isEnd() {
        return this.index == this.data.length;
    }

    public LexerState getState() {
        return state;
    }

    private Token setToken(Token t) {
        this.token = t;
        return t;
    }

    private char getCurrent() {
        return this.data[this.index];
    }

    private char getCurrentNext() {
        return this.data[this.index++];
    }

    private void eatSpace() {
        if (this.getCurrent() != ' ') return;
        while (this.getCurrent() == ' ') {
            this.index++;
        }
    }

    private String till(char tillChar) {
        StringBuilder sb = new StringBuilder();
        boolean isChar = this.getCurrent() == tillChar;
        boolean isLastCharEscape = this.getLast() == '\\';

        while (!this.isEnd() && !(isChar || isLastCharEscape) && this.getCurrent() != '$') {
            sb.append(this.getCurrentNext());
        }

        return sb.toString();
    }

    private String tillSpace() {
        return till(' ');
    }

    private char getLast() {
        return this.data[this.index - 1];
    }

    private char getNext() {
        return this.data[this.index + 1];
    }

    public Token getNextToken() {
        if (this.index > this.data.length) {
            return this.getCurrentToken();
        }

        if (this.isEnd()) {
            return this.setToken(new Token(TokenType.EOF, null));
        }

        if (this.state == LexerState.TAG) {
            this.eatSpace();

            // COMMAND
            if (this.token.getType().equals(TokenType.TAG_OPEN)) {
                if (this.getCurrent() == '=') {
                    return this.setToken(new Token(TokenType.SYMBOL, this.getCurrentNext()));
                }

                return this.setToken(new Token(TokenType.COMMAND, this.tillSpace()));
            }

            // END
            if (this.getCurrent() == '$') {
                this.state = LexerState.NORMAL;
                this.index++;
                this.index++;

                return this.setToken(new Token(TokenType.TAG_CLOSE, "$}"));
            }

            // FUNCTION
            if (this.getCurrent() == '@') {
                this.index++;
                String tmp = this.tillSpace();
                this.index++;
                return this.setToken(new Token(TokenType.FUNCTION, tmp));
            }

            // STRING LITERAL
            if (this.getCurrent() == '"') {
                this.index++;
                String tmp = this.till('"');
                this.index++;

                return this.setToken(new Token(TokenType.STRING, tmp));
            }

            // NUMBERS
            if (Character.isDigit(this.getCurrent())) {
                String tmp = this.tillSpace();

                try {
                    double d = Double.parseDouble(tmp);
                    if (Math.ceil(d) == Math.floor(d)) {
                        return this.setToken(new Token(TokenType.INTEGER, tmp));
                    }

                    return this.setToken(new Token(TokenType.DOUBLE, tmp));
                } catch (NumberFormatException ex) {
                    throw new LexerException("Number not good");
                }
            }

            return this.setToken(new Token(TokenType.STRING, tillSpace()));
        }

        if (isStartOfTag()) {
            this.state = LexerState.TAG;
            this.index++;
            this.index++;

            return this.setToken(new Token(TokenType.TAG_OPEN, "{$"));
        } else {
            StringBuilder sb = new StringBuilder();
            while(!this.isEnd() && !this.isStartOfTag()) {
                sb.append(this.getCurrentNext());
            }

            return this.setToken(new Token(TokenType.TEXT, sb.toString()));
        }
    }

    private boolean isStartOfTag() {
        return this.getCurrent() == '{' && this.getLast() != '\\' && this.getNext() == '$';
    }

    public Token getCurrentToken() {
        return this.token;
    }
}
