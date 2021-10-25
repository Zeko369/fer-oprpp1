package hr.fer.oprpp1.custom.scripting.lexer;

import hr.fer.oprpp1.custom.scripting.lexer.demo.Loader;

public class Lexer {
    public static void main(String[] args) {
        String code = Loader.loadCode("./demos/code.txt");
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

    private void checkStream(char... items) {
        for (char i : items) {
            if (this.getCurrent() != i) {
                throw new RuntimeException("Error stream");
            }

            this.index++;
        }
    }

    private String tillChar(char c) {
        StringBuilder sb = new StringBuilder();
        while (!this.isEnd() && this.getCurrent() != c) {
            sb.append(this.getCurrentNext());
        }

        return sb.toString();
    }

    private void eatSpace() {
        if (this.getCurrent() != ' ') return;
        while (this.getCurrent() == ' ') {
            this.index++;
        }
    }

    private String till(char tillChar) {
        StringBuilder sb = new StringBuilder();
        while (!this.isEnd() && this.getCurrent() != tillChar && this.getCurrent() != '$') {
            sb.append(this.getCurrentNext());
        }

        return sb.toString();
    }

    private String tillSpace() {
        return till(' ');
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

            // END
            if (this.getCurrent() == '$') {
                // FIXME: Deprecate this and use tillSpace
                this.checkStream('$', '}');
                this.state = LexerState.NORMAL;
                return this.setToken(new Token(TokenType.TAG_CLOSE, "$}"));
            }

            // COMMAND
            if (this.token.getType().equals(TokenType.TAG_OPEN)) {
                if (this.getCurrent() == '=') {
                    return this.setToken(new Token(TokenType.SYMBOL, this.getCurrentNext()));
                }

                return this.setToken(new Token(TokenType.COMMAND, this.tillSpace()));
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

        if (this.getCurrent() == '{') {
            this.checkStream('{', '$');

            this.state = LexerState.TAG;
            return this.setToken(new Token(TokenType.TAG_OPEN, "{$"));

//            boolean isEcho = this.getCurrent() == '=';
//            if (isEcho) {
//                this.index++;
//            }
//
//            String tmp = this.tillChar('$').trim();
//
//            this.checkStream('$', '}');
//
//            if (isEcho) {
//                return this.setToken(new Token(TokenType.ECHO, tmp));
//            } else {
//                TokenType type = tmp.toUpperCase(Locale.ROOT).equals("END")
//                        ? TokenType.TAG_CLOSE
//                        : TokenType.TAG_OPEN;
//                return this.setToken(new Token(type, tmp));
//            }
        } else {
            String tmp = this.tillChar('{').trim();
            return this.setToken(new Token(TokenType.TEXT, tmp));
        }
    }

    public Token getCurrentToken() {
        return this.token;
    }
}
