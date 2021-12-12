package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.util.List;

public class SymbolShellCommand implements ShellCommand {
    private static class InvalidSymbolNameException extends RuntimeException {
    }

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String[] args = ArgumentParser.parse(arguments, 1, 2);
        if (args.length == 1) {
            try {
                String output = String.format("Symbol for %s is '%c'", args[0], this.getSymbol(env, args[0]));
                env.writeln(output);
            } catch (InvalidSymbolNameException e) {
                env.errorln("Invalid symbol name.");
            }
            return ShellStatus.CONTINUE;
        }

        try {
            this.changeSymbol(env, args[0], args[1]);
        } catch (InvalidSymbolNameException e) {
            env.errorln("Invalid symbol name.");
        }

        return ShellStatus.CONTINUE;
    }

    Character getSymbol(Environment env, String symbolName) {
        return switch (symbolName) {
            case "PROMPT" -> env.getPromptSymbol();
            case "MULTILINE" -> env.getMultilineSymbol();
            case "MORELINES" -> env.getMorelinesSymbol();
            default -> throw new InvalidSymbolNameException();
        };
    }

    void changeSymbol(Environment env, String symbolName, String symbolValue) {
        env.writeln(String.format("Symbol for %s changed from '%c' to '%c'",
                symbolName,
                this.getSymbol(env, symbolName),
                symbolValue.charAt(0))
        );

        switch (symbolName) {
            case "PROMPT" -> env.setPromptSymbol(symbolValue.charAt(0));
            case "MULTILINE" -> env.setMultilineSymbol(symbolValue.charAt(0));
            case "MORELINES" -> env.setMorelinesSymbol(symbolValue.charAt(0));
            default -> throw new InvalidSymbolNameException();
        }
    }

    @Override
    public String getCommandName() {
        return "symbol";
    }

    @Override
    public List<String> getCommandDescription() {
        return List.of(
                "Command symbol can take one argument, which is a symbol name.",
                "Or it can take 2 arguments, which are symbol name and symbol new value."
        );
    }
}
