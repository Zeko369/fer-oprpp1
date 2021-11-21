package hr.fer.oprpp1.hw02.prob;

/**
 * The type Demo.
 *
 * @author franzekan
 */
public class Demo {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        String input = "Ovo je 123ica, ab57.\nKraj";
//        String input = "  Štefanija\r\n\t Automobil   ";
        String input = "\\1\\2 ab\\\\\\2c\\3\\4d";
        Lexer l = new Lexer(input);

        Token t;
        do {
            t = l.nextToken();
            System.out.println(t);
        } while (t.getType() != TokenType.EOF);
    }
}
