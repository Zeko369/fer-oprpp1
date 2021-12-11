package hr.fer.zemris.java.hw06.shell;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParserException;
import hr.fer.zemris.java.hw06.shell.commands.ShellCommand;

public class MyShell {
    public static void main(String[] args) {
        Environment env = new TerminalEnvironment();
        env.writeln("Welcome to MyShell v 1.0");

        ShellStatus status = ShellStatus.CONTINUE;
        while (status != ShellStatus.TERMINATE) {
            String line = env.readLine();
            String cmdName = line.trim();
            String cmdArgs = "";
            if (line.indexOf(' ') != -1) {
                cmdName = line.substring(0, line.indexOf(' '));
                cmdArgs = line.substring(line.indexOf(' ') + 1);
            }

            ShellCommand cmd = env.commands().get(cmdName);
            if (cmd == null) {
                env.writeln("Command not found.");
                continue;
            }

            try {
                status = cmd.executeCommand(env, cmdArgs);
            } catch (ArgumentParserException e) {
                System.out.println(e.getMessage());
            } catch (ShellIOException e) {
                e.printStackTrace();
            }
        }
    }
}
