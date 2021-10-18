package hr.fer.oprpp1.hw02.prob;

public class Demo {
    public static void main(String[] args) {
//        String input = "Ovo je 123ica, ab57.\nKraj";
        String input = "  Štefanija\r\n\t Automobil   ";
        Lexer l = new Lexer(input);

        Token t;
        do {
            t = l.nextToken();
            System.out.println(t);
        } while (t.getType() != TokenType.EOF);
    }
}
