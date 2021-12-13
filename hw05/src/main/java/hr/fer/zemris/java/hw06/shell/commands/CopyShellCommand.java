package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.commands.shared.FileLoader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * <code>copy</code> command, used for copying files
 *
 * @author franzekan
 */
public class CopyShellCommand implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String[] args = ArgumentParser.parse(arguments, 2);

        File src = new File(args[0]);
        if (!src.exists()) {
            env.errorln("File " + args[0] + " does not exist.");
            return ShellStatus.CONTINUE;
        }

        if (src.isDirectory()) {
            env.errorln("Cannot copy a directory.");
            return ShellStatus.CONTINUE;
        }

        File dest = new File(args[1]);
        if (dest.isDirectory()) {
            dest = new File(dest, src.getName());
        }

        if (dest.exists()) {
            env.write("File " + args[1] + " already exists. Overwrite? (y/n) ");
            String answer = env.readLine();
            if (!answer.equalsIgnoreCase("y")) {
                env.errorln("Copy cancelled.");
                return ShellStatus.CONTINUE;
            }
        }

        FileLoader loader = new FileLoader(args[0]);
        try (
                FileOutputStream fos = new FileOutputStream(dest);
                BufferedOutputStream bos = new BufferedOutputStream(fos)
        ) {
            loader.loadFile(bos::write);
        } catch (IOException e) {
            throw new ShellIOException(e);
        }

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "copy";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Copies the given file to the given location.");
    }
}
