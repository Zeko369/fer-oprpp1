package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public class HexdumpShellCommand implements ShellCommand {
    public static final int BYTES_PER_LINE = 16;

    public static void main(String[] args) {
        HexdumpShellCommand command = new HexdumpShellCommand();
        command.executeCommand(new Environment() {
            @Override
            public String readLine() throws ShellIOException {
                return null;
            }

            @Override
            public void write(String text) throws ShellIOException {
                System.out.print(text);
            }

            @Override
            public void writeln(String text) throws ShellIOException {
                System.out.println(text);
            }

            @Override
            public SortedMap<String, ShellCommand> commands() {
                return null;
            }

            @Override
            public Character getMultilineSymbol() {
                return null;
            }

            @Override
            public void setMultilineSymbol(Character symbol) {

            }

            @Override
            public Character getPromptSymbol() {
                return null;
            }

            @Override
            public void setPromptSymbol(Character symbol) {

            }

            @Override
            public Character getMorelinesSymbol() {
                return null;
            }

            @Override
            public void setMorelinesSymbol(Character symbol) {

            }
        }, "./foo.txt");
    }

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String args = ArgumentParser.parse(arguments, 1)[0];
        File file = new File(args);
        if (!file.exists()) {
            env.writeln("File not found");
            return ShellStatus.CONTINUE;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            int iter = 0;
            while (bis.available() > 0) {
                byte[] data = bis.readNBytes(BYTES_PER_LINE);
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%08X ", iter));

                for (int i = 0; i < BYTES_PER_LINE; i++) {
                    sb.append(data.length < i ? "  " : String.format("%02X", data[i]));
                    sb.append(i == BYTES_PER_LINE / 2 - 1 ? "|" : " ");
                }

                sb.append("| ");
                for (byte c : data) {
                    sb.append(c < 32 ? "." : (char) c);
                }

                env.writeln(sb.toString());
                iter += BYTES_PER_LINE;
            }

            bis.close();
        } catch (FileNotFoundException ex) {
            env.writeln("File not found");
            return ShellStatus.CONTINUE;
        } catch (IOException e) {
            throw new ShellIOException(e.getMessage());
        }

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
