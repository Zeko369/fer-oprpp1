package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.util.List;

public class HelpShellCommand implements ShellCommand {
    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String[] args = ArgumentParser.parse(arguments, 0, 1);
        if (args.length == 0) {
            env.commands().values().forEach(c -> env.writeln(c.getCommandName()));
        } else {
            ShellCommand sc = env.commands().get(args[0]);
            if (sc == null) {
                env.errorln("Command not found.");
                return ShellStatus.CONTINUE;
            }

            sc.getCommandDescription().forEach(env::writeln);
        }

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public List<String> getCommandDescription() {
        return List.of(
                "If called without arguments, prints all available commands.",
                "If called with one argument, prints description of the command."
        );
    }
}
