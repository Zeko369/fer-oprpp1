package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.commands.shared.FileLoader;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Collections;
import java.util.List;

/**
 * <code>cat</code> command, used for printing files
 *
 * @author franzekan
 */
public class CatShellCommand implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String[] args = ArgumentParser.parse(arguments, 1, 2);
        String filePath = args[0];

        CharsetDecoder decoder = (args.length == 2 ? Charset.forName(args[1]) : Charset.defaultCharset()).newDecoder();

        FileLoader fileLoader = new FileLoader(filePath);
        FileLoader.FileLoaderResponse res = fileLoader.loadFile(bytes -> {
                    try {
                        env.write(decoder.decode(ByteBuffer.wrap(bytes)).toString());
                    } catch (CharacterCodingException e) {
                        env.errorln("Error while decoding file to charset");
                    }
                }
        );
        env.writeln("");

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
