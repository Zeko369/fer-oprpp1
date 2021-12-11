package hr.fer.zemris.java.hw06.shell;

import hr.fer.zemris.java.hw06.shell.commands.*;

import java.util.*;

public class TerminalEnvironment implements Environment {
    private final SortedMap<String, ShellCommand> commandsMap;

    private Character promptSymbol;
    private Character multilineSymbol;
    private Character morelinesSymbol;


    public TerminalEnvironment() {
        SortedMap<String, ShellCommand> mutableCommandsMap = new TreeMap<>();
        List.of(new ShellCommand[]{
                new CharsetsShellCommand(),
                new ExitShellCommand(),
                new HexdumpShellCommand(),
                new LsShellCommand(),
                new MkdirShellCommand()
        }).forEach(command -> mutableCommandsMap.put(command.getCommandName(), command));

        this.commandsMap = Collections.unmodifiableSortedMap(mutableCommandsMap);
    }

    @Override
    public String readLine() throws ShellIOException {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        } catch (Exception e) {
            throw new ShellIOException(e);
        }
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
        return this.commandsMap;
    }

    @Override
    public Character getMultilineSymbol() {
        return this.multilineSymbol;
    }

    @Override
    public void setMultilineSymbol(Character symbol) {
        this.multilineSymbol = symbol;
    }

    @Override
    public Character getPromptSymbol() {
        return this.promptSymbol;
    }

    @Override
    public void setPromptSymbol(Character symbol) {
        this.promptSymbol = symbol;
    }

    @Override
    public Character getMorelinesSymbol() {
        return this.morelinesSymbol;
    }

    @Override
    public void setMorelinesSymbol(Character symbol) {
        this.morelinesSymbol = symbol;
    }
}
