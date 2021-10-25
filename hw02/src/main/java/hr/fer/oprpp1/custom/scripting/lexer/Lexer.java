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

    private static char[] VALID_OPERATORS = {'+', '-', '/', '*', '^'};

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

    private void eatSpace() {
        if (this.getCurrent() != ' ') return;
        while (this.getCurrent() == ' ') {
            this.index++;
        }
    }

    private String till(char tillChar) {
        StringBuilder sb = new StringBuilder();
        boolean isChar = this.getCurrent() == tillChar;

        while (!this.isEnd()) {
            if(this.getCurrent() == '$') {
                break;
            }

            if(this.getCurrent() == tillChar) {
                break;
            }

            sb.append(this.getCurrentNext());
        }

        return sb.toString();
    }

    private String tillSpace() {
        return till(' ');
    }

    private boolean hasLast() {
        return this.index > 0;
    }

    private char getLast() {
        return this.data[this.index - 1];
    }

    private char getCurrent() {
        return this.data[this.index];
    }

    private char getCurrentNext() {
        return this.data[this.index++];
    }

    private boolean hasNext() {
        return this.data.length > this.index;
    }

    private char getNext() {
        return this.data[this.index + 1];
    }

    private boolean isStartOfTag() {
        return this.getCurrent() == '{'
                && (!this.hasLast() || this.getLast() != '\\')
                && (!this.hasNext() || this.getNext() == '$');
    }

    private boolean isSymbol(char sym) {
        for(char c : VALID_OPERATORS) {
            if(c == sym) {
                return true;
            }
        }

        return false;
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

            String tmp = tillSpace();
            if(tmp.length() == 0) {
                throw new LexerException("This empty??");
            }

            if(!Character.isLetter(tmp.charAt(0))) {
                if(tmp.length() == 1 && this.isSymbol(tmp.charAt(0))) {
                    return this.setToken(new Token(TokenType.SYMBOL, tmp));
                }

                throw new LexerException("This is not a valid variable name");
            }

            return this.setToken(new Token(TokenType.VARIABLE, tmp));
        }

        if (isStartOfTag()) {
            this.state = LexerState.TAG;
            this.index++;
            this.index++;

            return this.setToken(new Token(TokenType.TAG_OPEN, "{$"));
        } else {
            StringBuilder sb = new StringBuilder();
            while (!this.isEnd() && !this.isStartOfTag()) {
                sb.append(this.getCurrentNext());
            }

            return this.setToken(new Token(TokenType.TEXT, sb.toString()));
        }
    }

    public Token getCurrentToken() {
        return this.token;
    }
}
