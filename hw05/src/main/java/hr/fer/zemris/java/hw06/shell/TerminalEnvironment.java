package hr.fer.zemris.java.hw06.shell;

import hr.fer.zemris.java.hw06.shell.commands.*;

import java.util.*;

public class TerminalEnvironment implements Environment {
    private final SortedMap<String, ShellCommand> commandsMap;

    private Character promptSymbol = '>';
    private Character multilineSymbol = '|';
    private Character morelinesSymbol = '\\';

    private final Scanner sc;

    public TerminalEnvironment() {
        SortedMap<String, ShellCommand> mutableCommandsMap = new TreeMap<>();
        List.of(new ShellCommand[]{
                new CatShellCommand(),
                new CharsetsShellCommand(),
                new CopyShellCommand(),
                new ExitShellCommand(),
                new HexdumpShellCommand(),
                new LsShellCommand(),
                new MkdirShellCommand(),
                new SymbolShellCommand(),
                new TreeShellCommand(),
        }).forEach(command -> mutableCommandsMap.put(command.getCommandName(), command));

        this.commandsMap = Collections.unmodifiableSortedMap(mutableCommandsMap);
        this.sc = new Scanner(System.in);
    }

    @Override
    public String readLine() throws ShellIOException {
        System.out.printf("%s ", this.promptSymbol);

        StringBuilder line = new StringBuilder(this.sc.nextLine());
        while (line.toString().endsWith(String.valueOf(this.morelinesSymbol))) {
            line.deleteCharAt(line.length() - 1);

            System.out.printf("%s ", this.multilineSymbol);
            line.append(this.sc.nextLine());
        }

        return line.toString();
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
    public void errorln(String text) throws ShellIOException {
        // TODO: Figure out sync between stdout and stderr
        System.out.println(PrintColorUtils.colorPrint(text, PrintColorUtils.Color.RED));
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
