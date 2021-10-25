package hr.fer.oprpp1.custom.scripting.parser;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.collections.ObjectStack;
import hr.fer.oprpp1.custom.scripting.elems.*;
import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.lexer.Token;
import hr.fer.oprpp1.custom.scripting.lexer.TokenType;
import hr.fer.oprpp1.custom.scripting.node.*;

public class SmartScriptParser {
    private final String body;
    private DocumentNode root;

    public SmartScriptParser(String body) {
        this.body = body;
        this.root = new DocumentNode();

        this.parse();
    }

    public DocumentNode getDocumentNode() {
        return this.root;
    }

    // TODO: EXTRACT
    private boolean isOneOfType(Token token, TokenType[] types) {
        for (TokenType t : types) {
            if (token.getType().equals(t)) {
                return true;
            }
        }

        return false;
    }

    private Element parseElement(Token token) {
        return switch (token.getType()) {
            case INTEGER -> new ElementConstantInteger((Integer) token.getValue());
            case DOUBLE -> new ElementConstantDouble((Double) token.getValue());
            case STRING -> new ElementString((String) token.getValue());
            case OPERATOR -> new ElementOperator((String) token.getValue());
            case VARIABLE -> new ElementVariable((String) token.getValue());
            case FUNCTION -> new ElementFunction((String) token.getValue());
            default -> throw new SmartScriptParserException(String.format("Token inside of echo tag is not supported element\nFound: %s", token.getType()));
        };
    }

    // TODO: Share this args
    private Element[] getElementsInTag(Lexer l) {
        ArrayIndexedCollection elements = new ArrayIndexedCollection();
        Token t = l.getNextToken();

        while (t.getType() != TokenType.EOF && t.getType() != TokenType.TAG_CLOSE) {
            elements.add(this.parseElement(t));
            t = l.getNextToken();
        }

        Element[] tmp = new Element[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            tmp[i] = (Element) elements.get(i);
        }

        return tmp;
    }

    // FIXME: TILL SPACE IS BROKEN SINCE 123" won't work but is valid (break on next type)

    private void parse() {
        ObjectStack s = new ObjectStack();
        s.push(this.root);

        Lexer l = new Lexer(this.body);
        Token t;

        do {
            t = l.getNextToken();

            switch (t.getType()) {
                case TEXT -> ((Node) s.peek()).addChildNode(new TextNode((String) t.getValue()));
                case TAG_OPEN -> {
                    t = l.getNextToken();

                    switch (t.getType()) {
                        case SYMBOL -> ((Node) s.peek()).addChildNode(new EchoNode(this.getElementsInTag(l)));
                        case COMMAND -> {
                            switch ((String) t.getValue()) {
                                case "FOR" -> {
                                    Element[] elements = this.getElementsInTag(l);
                                    if (elements.length < 3) {
                                        throw new SmartScriptParserException("FOR: Too few arguments");
                                    }

                                    if (elements.length > 4) {
                                        throw new SmartScriptParserException("FOR: Too many arguments");
                                    }

                                    if (!(elements[0] instanceof ElementVariable)) {
                                        throw new SmartScriptParserException("FOR: First argument isn't a variable");
                                    }

                                    for (int i = 1; i < elements.length; i++) {
                                        if (!(elements[i] instanceof ElementVariable || elements[i] instanceof ElementConstantInteger || elements[i] instanceof ElementConstantDouble)) {
                                            throw new SmartScriptParserException(String.format("FOR: %d is not a valid type", i));
                                        }
                                    }

                                    ForLoopNode fln = new ForLoopNode((ElementVariable) elements[0], elements[1], elements[2], elements.length == 3 ? null : elements[3]);
                                    ((Node) s.peek()).addChildNode(fln);
                                    s.push(fln);
                                }
                                case "END" -> {
                                    t = l.getNextToken();
                                    s.pop();
                                }
                                default -> throw new SmartScriptParserException("Command not implemented");
                            }
                        }
                        default -> throw new SmartScriptParserException("Vidi sta da tu napises");
                    }
                }
                case TAG_CLOSE -> throw new SmartScriptParserException("Expected a TagOpen before this close");
            }
        } while (t.getType() != TokenType.EOF);
    }
}
