package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.commands.shared.FileLoader;

import java.util.Collections;
import java.util.List;

/**
 * <code>hexdump</code> command, used for printing out the hex value of the file
 *
 * @author franzekan
 */
public class HexdumpShellCommand implements ShellCommand {
    /**
     * The constant BYTES_PER_LINE.
     */
    public static final int BYTES_PER_LINE = 16;

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String filePath = ArgumentParser.singleParamParse(arguments);
        FileLoader fileLoader = new FileLoader(filePath, BYTES_PER_LINE);

        fileLoader.loadFile(new FileLoader.FileLoaderListener() {
            int iter = 0;

            @Override
            public void onBytes(byte[] bytes) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%08X ", iter));

                for (int i = 0; i < BYTES_PER_LINE; i++) {
                    sb.append(bytes.length - 1 < i ? "  " : String.format("%02X", bytes[i]));
                    sb.append(i == BYTES_PER_LINE / 2 - 1 ? "|" : " ");
                }

                sb.append("| ");
                for (byte c : bytes) {
                    sb.append(c < 32 ? "." : (char) c);
                }

                env.writeln(sb.toString());
                iter += BYTES_PER_LINE;
            }
        });

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "hexdump";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Command hexdump prints hexadecimal representation of the given file.");
    }
}
