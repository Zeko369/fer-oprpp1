package hr.fer.oprpp1.custom.scripting.parser.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    @Test
    public void throwsAnErrorTest() {
        assertThrows(UnsupportedOperationException.class, () -> CommandParser.parseCommand(null));
    }
}
