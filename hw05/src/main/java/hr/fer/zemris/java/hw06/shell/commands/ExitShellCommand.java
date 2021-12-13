package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.util.Collections;
import java.util.List;

/**
 * <code>exit</code> command, used for exiting the shell.
 *
 * @author franzekan
 */
public class ExitShellCommand implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        ArgumentParser.parse(arguments, 0);
        return ShellStatus.TERMINATE;
    }

    @Override
    public String getCommandName() {
        return "exit";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Takes no arguments and exits the shell.");
    }
}
