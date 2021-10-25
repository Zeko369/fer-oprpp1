package hr.fer.oprpp1.custom.scripting.parser.commands;

import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementConstantDouble;
import hr.fer.oprpp1.custom.scripting.elems.ElementConstantInteger;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;
import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.node.ForLoopNode;
import hr.fer.oprpp1.custom.scripting.node.Node;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import hr.fer.oprpp1.custom.scripting.parser.utils.ElementsHelper;

public class ForCommandParser implements CommandParser {
    public static Node parseCommand(Lexer l) {
        Element[] elements = ElementsHelper.getElementsInTag(l);
        if (elements.length < 3) {
            throw new SmartScriptParserException("[FOR]: Too few arguments");
        }

        if (elements.length > 4) {
            throw new SmartScriptParserException("[FOR]: Too many arguments");
        }

        if (!(elements[0] instanceof ElementVariable)) {
            throw new SmartScriptParserException("[FOR]: First argument isn't a variable");
        }

        for (int i = 1; i < elements.length; i++) {
            if (!(elements[i] instanceof ElementVariable || elements[i] instanceof ElementConstantInteger || elements[i] instanceof ElementConstantDouble)) {
                throw new SmartScriptParserException(String.format("[FOR]: %d is not a valid type", i));
            }
        }

        return new ForLoopNode((ElementVariable) elements[0], elements[1], elements[2], elements.length == 3 ? null : elements[3]);
    }
}
