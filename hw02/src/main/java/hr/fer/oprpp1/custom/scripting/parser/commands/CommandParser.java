package hr.fer.oprpp1.custom.scripting.parser.commands;

import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.node.Node;

/**
 * Interface for commands inside the code
 *
 * @author franzekan
 */
public interface CommandParser {
    /**
     * Parse command node.
     *
     * @param l Lexer
     * @return parsed Command node
     */
    static Node parseCommand(Lexer l) {
        throw new UnsupportedOperationException("Direct usage of CommandParser is not supported");
    }
}
