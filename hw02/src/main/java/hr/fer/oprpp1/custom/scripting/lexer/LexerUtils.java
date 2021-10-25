package hr.fer.oprpp1.custom.scripting.lexer;

public class LexerUtils {
    public static boolean isVariable(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '_';
    }

    public static boolean isNumber(char c) {
        return Character.isDigit(c) || c == '.';
    }

}
