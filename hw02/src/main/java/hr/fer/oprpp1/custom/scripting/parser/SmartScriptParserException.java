package hr.fer.oprpp1.custom.scripting.parser;

/**
 * Custom exception thrown inside Parser
 *
 * @author franzekan
 */
public class SmartScriptParserException extends RuntimeException {
    /**
     * Instantiates a new Smart script parser exception.
     *
     * @param message the message
     */
    public SmartScriptParserException(String message) {
        super(message);
    }
}
