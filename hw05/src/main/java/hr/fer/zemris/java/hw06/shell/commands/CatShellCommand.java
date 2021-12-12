package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.commands.shared.FileLoader;

import java.util.Collections;
import java.util.List;

public class CatShellCommand implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String filePath = ArgumentParser.singleParamParse(arguments);
        FileLoader fileLoader = new FileLoader(filePath);
        FileLoader.FileLoaderResponse res = fileLoader.loadFile(bytes -> {
            for (byte c : bytes) {
                System.out.print((char) c);
            }
        });

        if (res == FileLoader.FileLoaderResponse.NOT_FOUND) {
            env.errorln("File " + filePath + " not found.");
        }

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "cat";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Prints the contents of the file to the standard output.");
    }
}
