package hr.fer.oprpp1.custom.scripting.parser.utils;

import hr.fer.oprpp1.custom.scripting.elems.*;
import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.lexer.Token;
import hr.fer.oprpp1.custom.scripting.lexer.TokenType;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementsHelperTest {
    @Test
    public void getsElementsInTagTest() {
        Lexer l = new Lexer("{$= foo 0 123.123 \"asd\" @command + $}");
        assertEquals(TokenType.TAG_OPEN, l.getNextToken().getType());
        assertEquals(TokenType.SYMBOL, l.getNextToken().getType());

        Element[] elements = ElementsHelper.getElementsInTag(l);
        assertEquals(6, elements.length);

        assertInstanceOf(ElementVariable.class, elements[0]);
        assertInstanceOf(ElementConstantInteger.class, elements[1]);
        assertInstanceOf(ElementConstantDouble.class, elements[2]);
        assertInstanceOf(ElementString.class, elements[3]);
        assertInstanceOf(ElementFunction.class, elements[4]);
        assertInstanceOf(ElementOperator.class, elements[5]);
    }

    @Test
    public void throwsIfNotAValidElementTest() {
        class TmpLexer extends Lexer {
            public TmpLexer(String str) {super(str);}

            @Override
            public Token getNextToken() {
                return new Token(TokenType.TEXT, "Something");
            }
        }

        assertThrows(SmartScriptParserException.class, () -> ElementsHelper.getElementsInTag(new TmpLexer("")));
    }
}

