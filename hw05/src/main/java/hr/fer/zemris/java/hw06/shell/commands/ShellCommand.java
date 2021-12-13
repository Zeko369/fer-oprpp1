package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.util.List;

/**
 * The interface Shell command.
 *
 * @author franzekan
 */
public interface ShellCommand {
    /**
     * Execute command shell status.
     *
     * @param env       the env
     * @param arguments the arguments
     * @return the shell status
     */
    ShellStatus executeCommand(Environment env, String arguments);

    /**
     * Gets command name.
     *
     * @return the command name
     */
    String getCommandName();

    /**
     * Gets command description.
     *
     * @return the command description
     */
    List<String> getCommandDescription();
}
