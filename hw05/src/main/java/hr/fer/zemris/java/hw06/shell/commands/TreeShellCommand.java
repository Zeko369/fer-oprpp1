package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.List;

public class TreeShellCommand implements ShellCommand {
    private interface PrintCallback {
        void print(String s);
    }

    private static final class TreeWalker implements FileVisitor<Path> {
        private int depth = 0;
        private final PrintCallback callback;

        public TreeWalker(PrintCallback callback) {
            this.callback = callback;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            callback.print(String.format("%s%s%n", " ".repeat(this.depth * 2), dir.getFileName()));
            this.depth++;

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            callback.print(String.format("%s%s%n", " ".repeat(this.depth * 2), file.getFileName()));

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            this.depth--;
            return FileVisitResult.CONTINUE;
        }
    }

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String path = ArgumentParser.singleParamParse(arguments);
        File tree = new File(path);

        if (!tree.exists()) {
            env.errorln("Given path does not exist.");
            return ShellStatus.CONTINUE;
        }

        if (!tree.isDirectory()) {
            env.errorln("Given path is not a directory.");
            return ShellStatus.CONTINUE;
        }

        TreeWalker walker = new TreeWalker(env::writeln);
        try {
            Files.walkFileTree(tree.toPath(), walker);
            return ShellStatus.CONTINUE;
        } catch (IOException e) {
            throw new ShellIOException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "tree";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Prints directory tree.");
    }
}
