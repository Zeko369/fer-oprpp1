package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParserException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitShellCommandTest {
    private final ShellCommand command = new ExitShellCommand();

    @Test
    void executeCommandTest() {
        ShellStatus status = this.command.executeCommand(null, "");
        assertEquals(ShellStatus.TERMINATE, status);
    }

    @Test
    void executeCommandThrowsOnWrongArgsTest() {
        assertThrows(ArgumentParserException.class, () -> this.command.executeCommand(null, "foo bar"));
    }

    @Test
    void getCommandNameTest() {
        assertEquals("exit", this.command.getCommandName());
    }

    @Test
    void getCommandDescriptionTest() {
        assertEquals(1, this.command.getCommandDescription().size());
    }
}
