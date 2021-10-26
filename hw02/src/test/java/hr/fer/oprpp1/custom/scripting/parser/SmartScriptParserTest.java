package hr.fer.oprpp1.custom.scripting.parser;


import hr.fer.oprpp1.custom.scripting.elems.*;
import hr.fer.oprpp1.custom.scripting.node.DocumentNode;
import hr.fer.oprpp1.custom.scripting.node.EchoNode;
import hr.fer.oprpp1.custom.scripting.node.ForLoopNode;
import hr.fer.oprpp1.custom.scripting.node.TextNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartScriptParserTest {
    // MOST IMPORTANT
    @Test
    public void exampleParseAndReparse() {
        String input = "Sample text\n{$ FOR i 1 10 1 $}\n This is {$= i $}-th time this message is generated.\n{$   END $}\n{$FOR i 0 10 2 $}\n  sin({$=i$}^2) = {$= i i * @sin  \"0.000\" @decfmt $}\n {$END$}";

        SmartScriptParser p = new SmartScriptParser(input);
        DocumentNode node = p.getDocumentNode();

        String parsedToString = node.toString();

        p = new SmartScriptParser(parsedToString);
        node = p.getDocumentNode();

        assertEquals(parsedToString, node.toString());
    }

    @Test
    public void throwsForNullInputTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser(null));
    }

    @Test
    public void returnsEOFTokenTest() {
        SmartScriptParser p = new SmartScriptParser("");
        DocumentNode node = p.getDocumentNode();

        assertEquals(0, node.numberOfChildren());
    }

    @Test
    public void simpleNoTagsTest() {
        SmartScriptParser p = new SmartScriptParser("Foobar");
        DocumentNode node = p.getDocumentNode();

        assertEquals(1, node.numberOfChildren());
        assertEquals("Foobar", node.getChild(0).toCode());
        assertInstanceOf(TextNode.class, node.getChild(0));
    }

    @Test
    public void simpleEchoTagTest() {
        SmartScriptParser p = new SmartScriptParser("{$= 123 + 10.123 @func variable \"string\" $}");
        DocumentNode node = p.getDocumentNode();

        assertEquals(1, node.numberOfChildren());
        assertInstanceOf(EchoNode.class, node.getChild(0));

        Element[] elements = ((EchoNode) node.getChild(0)).getElements();
        assertEquals(6, elements.length);
        assertInstanceOf(ElementConstantInteger.class, elements[0]);
        assertInstanceOf(ElementOperator.class, elements[1]);
        assertInstanceOf(ElementConstantDouble.class, elements[2]);
        assertInstanceOf(ElementFunction.class, elements[3]);
        assertInstanceOf(ElementVariable.class, elements[4]);
        assertInstanceOf(ElementString.class, elements[5]);
    }

    @Test
    public void simpleForLoopTest() {
        SmartScriptParser p = new SmartScriptParser("{$ FoR i 0 10 1 $} Foo Bar {$ END $}");
        DocumentNode node = p.getDocumentNode();

        assertEquals(1, node.numberOfChildren());
        assertInstanceOf(ForLoopNode.class, node.getChild(0));

        ForLoopNode forLoop = (ForLoopNode) node.getChild(0);
        assertEquals(new ElementVariable("i"), forLoop.getVariable());
        assertEquals(new ElementConstantInteger(0), forLoop.getStartExpression());
        assertEquals(new ElementConstantInteger(10), forLoop.getEndExpression());
        assertEquals(new ElementConstantInteger(1), forLoop.getStepExpression());

        assertEquals(1, forLoop.numberOfChildren());
        assertInstanceOf(TextNode.class, forLoop.getChild(0));
        assertEquals(" Foo Bar ", forLoop.getChild(0).toCode());
    }

    @Test
    public void throwsErrorForInvalidNumberTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$= 100000000000000000000000 $}"));
    }

    @Test
    public void throwsErrorForInvalidVariableNameTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$= _asd $}"));
    }

    @Test
    public void throwsErrorForBackslashNInTextTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("Foo \\n asd"));
    }

    @Test
    public void throwsErrorForUnsupportedCommandTagTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ WHILE $}"));
    }

    @Test
    public void throwsErrorForUnclosedTagTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR i 0 10 1 $}"));
    }
}

