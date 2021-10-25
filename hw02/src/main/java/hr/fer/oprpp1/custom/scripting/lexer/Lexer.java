package hr.fer.oprpp1.custom.scripting.lexer;

import java.util.function.Predicate;

public class Lexer {
    private int index = 0;
    private final char[] data;
    private LexerState state = LexerState.NORMAL;
    private Token token = null;

    private static final char[] VALID_OPERATORS = {'+', '-', '/', '*', '^'};

    public Lexer(String str) {
        this.data = str.toCharArray();
    }

    private boolean isEnd() {
        return this.index == this.data.length;
    }

    public LexerState getState() {
        return state;
    }

    private Token setToken(TokenType type, Object value) {
        this.token = new Token(type, value);
        return this.token;
    }

    private void skipSpace() {
        if (this.getCurrent() != ' ') return;
        while (this.getCurrent() == ' ') {
            this.index++;
        }
    }

    private String tillNewType(Predicate<Character> test) {
        StringBuilder sb = new StringBuilder();
        while (!this.isEnd() && test.test(this.getCurrent()) && this.getCurrent() != ' ' && !this.isStartOfTag()) {
            sb.append(this.getCurrentAndMove());
        }

        return sb.toString();
    }

    private String getTillChar(char tillChar) {
        StringBuilder sb = new StringBuilder();
        while (!this.isEnd()) {
            if (this.getCurrent() == '$' || this.getCurrent() == tillChar) {
                break;
            }

            sb.append(this.getCurrentAndMove());
        }

        return sb.toString();
    }

    private String getTillSpace() {
        return getTillChar(' ');
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

    private char getCurrentAndMove() {
        return this.data[this.index++];
    }

    private char getNext() {
        return this.data[this.index + 1];
    }

    private boolean isStartOfTag() {
        return this.getCurrent() == '{' && (!this.hasLast() || this.getLast() != '\\') && this.getNext() == '$';
    }

    private boolean isOperator(char op) {
        for (char c : VALID_OPERATORS) {
            if (c == op) {
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
            return this.setToken(TokenType.EOF, null);
        }

        if (this.state == LexerState.TAG) {
            this.skipSpace();

            // COMMAND
            if (this.token.getType().equals(TokenType.TAG_OPEN)) {
                if (this.getCurrent() == '=') {
                    return this.setToken(TokenType.SYMBOL, this.getCurrentAndMove());
                }

                return this.setToken(TokenType.COMMAND, this.getTillSpace().toUpperCase());
            }

            // END
            if (this.getCurrent() == '$') {
                this.state = LexerState.NORMAL;
                this.index++;
                this.index++;

                return this.setToken(TokenType.TAG_CLOSE, "$}");
            }

            // FUNCTION
            if (this.getCurrent() == '@') {
                this.index++;
                String tmp = this.getTillSpace();
                this.index++;
                return this.setToken(TokenType.FUNCTION, tmp);
            }

            // STRING LITERAL
            if (this.getCurrent() == '"') {
                this.index++;
                String tmp = this.getTillChar('"');
                this.index++;

                return this.setToken(TokenType.STRING, tmp);
            }

            // NUMBERS
            if ((this.getCurrent() == '-' && Character.isDigit(this.getNext())) || Character.isDigit(this.getCurrent())) {
                boolean negate = this.getCurrent() == '-';
                if (negate) this.index++;

                String tmp = this.tillNewType(LexerUtils::isNumber);

                try {
                    double d = Double.parseDouble(tmp);
                    if (negate) d *= -1;

                    if (Math.ceil(d) == Math.floor(d)) {
                        return this.setToken(TokenType.INTEGER, (int) d);
                    }

                    return this.setToken(TokenType.DOUBLE, d);
                } catch (NumberFormatException ex) {
                    throw new LexerException("Number parse error");
                }
            }

            // OPERATOR
            if (this.isOperator(this.getCurrent())) {
                return this.setToken(TokenType.OPERATOR, String.valueOf(this.getCurrentAndMove()));
            }

            // VARIABLE
            if (!Character.isLetter(this.getCurrent())) {
                throw new LexerException("Unexpected character");
            }

            String tmp = this.tillNewType(LexerUtils::isVariable);
            if (tmp.length() == 0) {
                throw new LexerException("Variable is empty");
            }

            return this.setToken(TokenType.VARIABLE, tmp);
        }

        if (isStartOfTag()) {
            this.state = LexerState.TAG;
            this.index++;
            this.index++;

            return this.setToken(TokenType.TAG_OPEN, "{$");
        } else {
            StringBuilder sb = new StringBuilder();
            while (!this.isEnd() && !this.isStartOfTag()) {
                sb.append(this.getCurrentAndMove());
            }

            return this.setToken(TokenType.TEXT, sb.toString());
        }
    }

    public Token getCurrentToken() {
        return this.token;
    }
}
