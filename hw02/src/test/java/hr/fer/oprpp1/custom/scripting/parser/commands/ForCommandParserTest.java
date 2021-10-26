package hr.fer.oprpp1.custom.scripting.parser.commands;

import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParser;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForCommandParserTest {
    @Test
    public void parseForTooManyArgsTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR i 0 1 2 3 4 $}"));
    }

    @Test
    public void parseForTooFewArgsTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR i 0 $}"));
    }

    @Test
    public void parseForFirstArgIsntVariableTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR \"i\" 0 1 $}"));
    }

    @Test
    public void parseForWrongArgTypeTest() {
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR i 0 \"10\" $}"));
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR i 0 @func $}"));
        assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser("{$ FOR i 0 + $}"));
    }
}
