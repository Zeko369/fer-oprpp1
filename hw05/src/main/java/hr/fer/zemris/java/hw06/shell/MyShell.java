package hr.fer.zemris.java.hw06.shell;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParserException;
import hr.fer.zemris.java.hw06.shell.commands.ShellCommand;

/**
 * Entry point to out shell
 *
 * @author franzekan
 */
public class MyShell {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Environment env = new TerminalEnvironment();
        env.writeln(PrintColorUtils.colorPrint("Welcome to MyShell v 1.0", PrintColorUtils.Color.CYAN));

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
                env.errorln(e.getMessage());
            } catch (Exception e) {
                if (e instanceof ShellIOException) {
                    env.errorln("ShellIO Error occurred");
                } else {
                    env.errorln("Error occurred");
                }

                status = ShellStatus.TERMINATE;
            }
        }

        env.writeln(PrintColorUtils.colorPrint("Goodbye!", PrintColorUtils.Color.PURPLE));
    }
}
