package hr.fer.oprpp1.custom.scripting.parser;

import hr.fer.oprpp1.custom.collections.ObjectStack;
import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.lexer.LexerException;
import hr.fer.oprpp1.custom.scripting.lexer.Token;
import hr.fer.oprpp1.custom.scripting.lexer.TokenType;
import hr.fer.oprpp1.custom.scripting.node.*;
import hr.fer.oprpp1.custom.scripting.parser.commands.ForCommandParser;
import hr.fer.oprpp1.custom.scripting.parser.utils.ElementsHelper;

/**
 * SmartScript parser class that returns a parsed tree from a string
 *
 * @author franzekan
 */
public class SmartScriptParser {
    private final String body;
    private final DocumentNode root;

    /**
     * Instantiates a new Smart script parser.
     *
     * @param body the body
     */
    public SmartScriptParser(String body) {
        this.body = body;
        this.root = new DocumentNode();

        try {
            this.parse();
        } catch (SmartScriptParserException ex) {
            throw ex;
        } catch (LexerException ex) {
            throw new SmartScriptParserException(String.format("[Lexer]: %s", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SmartScriptParserException("Internal error");
        }
    }

    /**
     * Gets the root of the parsed tree
     *
     * @return the document node
     */
    public DocumentNode getDocumentNode() {
        return this.root;
    }

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
                        case SYMBOL -> ((Node) s.peek()).addChildNode(new EchoNode(ElementsHelper.getElementsInTag(l)));
                        case COMMAND -> {
                            switch ((String) t.getValue()) {
                                case "FOR" -> {
                                    Node forNode = ForCommandParser.parseCommand(l);

                                    ((Node) s.peek()).addChildNode(forNode);
                                    s.push(forNode);
                                }
                                case "END" -> {
                                    t = l.getNextToken();
                                    s.pop();
                                }
                                default -> throw new SmartScriptParserException("[Tag]: Command not implemented");
                            }
                        }
                        default -> throw new SmartScriptParserException("[General]: Tag type not supported");
                    }
                }
                case TAG_CLOSE -> throw new SmartScriptParserException("[General]: Expected a TagOpen before this close");
                case EOF -> {
                }
                default -> throw new SmartScriptParserException(String.format("[General]: Unexpected token: %s", t));
            }
        } while (t.getType() != TokenType.EOF);
    }
}
