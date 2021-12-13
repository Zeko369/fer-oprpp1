package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class MkdirShellCommand implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String folderPath = ArgumentParser.singleParamParse(arguments);

        File file = new File(folderPath);
        if (file.exists()) {
            env.errorln("File already exists.");
            return ShellStatus.CONTINUE;
        }

        if (file.mkdirs()) {
            return ShellStatus.CONTINUE;
        }

        env.errorln("Unable to create directory.");
        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "mkdir";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Makes new directory.");
    }
}
