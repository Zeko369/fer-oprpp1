package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;

public class LsShellCommand implements ShellCommand {

    public static void main(String[] args) {
        LsShellCommand ls = new LsShellCommand();
        ls.executeCommand(null, "/home/franzekan/Advent-of-code-2021");
    }

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        File dir = new File(arguments);
        if (!dir.isDirectory()) {
            throw new ShellIOException("Given path is not a directory.");
        }

        File[] files = dir.listFiles();
        assert files != null;

        class TmpFile {
            public final String permissions;
            public final String date;
            public final File file;

            public TmpFile(File file, String permissions, String date) {
                this.permissions = permissions;
                this.date = date;
                this.file = file;
            }
        }

        Arrays.stream(files).sorted(Comparator.comparing(File::getName)).map(f -> {
            String sb = (f.isDirectory() ? "d" : "-") + (f.canRead() ? "r" : "-") + (f.canWrite() ? "w" : "-") + (f.canExecute() ? "x" : "-");

            BasicFileAttributeView faView = Files.getFileAttributeView(f.toPath(), BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            String formattedDateTime;
            try {
                BasicFileAttributes attributes = faView.readAttributes();
                FileTime fileTime = attributes.creationTime();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
            } catch (IOException e) {
                formattedDateTime = "-1";
            }

            return (new TmpFile(f, sb, formattedDateTime));
        }).forEach(obj -> System.out.printf("%s %s%s %s %s\n", obj.permissions, " ".repeat(10 - Long.valueOf(obj.file.length()).toString().length()), obj.file.length(), obj.date, obj.file.getName()));

        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "ls";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Lists the files in the current directory.");
    }
}
