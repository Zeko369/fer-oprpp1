package hr.fer.oprpp1.hw02.demo;

import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.lexer.LexerState;
import hr.fer.oprpp1.custom.scripting.lexer.Token;
import hr.fer.oprpp1.custom.scripting.lexer.TokenType;
import hr.fer.oprpp1.custom.shared.FileLoader;

/**
 * LexerDemo
 * Standalone tester for Lexer
 *
 * @author franzekan
 */
public class LexerDemo {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String code = FileLoader.loadCode("./demos/primjer7.txt");
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
}
