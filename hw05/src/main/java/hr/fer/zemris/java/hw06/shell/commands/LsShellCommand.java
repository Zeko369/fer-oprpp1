package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class LsShellCommand implements ShellCommand {

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        File dir = new File(arguments);
        if (!dir.isDirectory()) {
            throw new ShellIOException("Given path is not a directory.");
        }

        File[] files = dir.listFiles();

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "ls";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Lists the files in the current directory.");
    }
}
