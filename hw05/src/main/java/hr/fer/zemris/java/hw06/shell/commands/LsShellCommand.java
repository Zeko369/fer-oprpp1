package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ArgumentParser.ArgumentParser;
import hr.fer.zemris.java.hw06.shell.Environment;
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

/**
 * <code>ls</code> command, used for listing all the files/directories in a specific directory.
 *
 * @author franzekan
 */
public class LsShellCommand implements ShellCommand {
    private static class FileRow {
        /**
         * The Permissions.
         */
        public final String permissions;
        /**
         * The File size.
         */
        public final String fileSize;
        /**
         * The Date.
         */
        public final String date;
        /**
         * The File name.
         */
        public final String fileName;

        /**
         * Instantiates a new File row.
         *
         * @param file        the file
         * @param permissions the permissions
         * @param date        the date
         */
        public FileRow(File file, String permissions, String date) {
            this.permissions = permissions;

            String size = Long.valueOf(file.length()).toString();
            this.fileSize = " ".repeat(10 - size.length()) + size;

            this.date = date;
            this.fileName = file.getName();
        }
    }


    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        String filePath = ArgumentParser.singleParamParse(arguments);

        File dir = new File(filePath);
        if (!dir.isDirectory()) {
            env.errorln("Given path is not a directory.");
            return ShellStatus.CONTINUE;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            env.errorln("Can't read files");
            return ShellStatus.CONTINUE;
        }

        Arrays.stream(files)
                .sorted(Comparator.comparing(File::getName))
                .map(f -> new FileRow(f, this.getPermissions(f), this.getFileCreatedAt(f)))
                .map(f -> String.format("%s %s %s %s", f.permissions, f.fileSize, f.date, f.fileName))
                .forEach(env::writeln);

        return ShellStatus.CONTINUE;
    }

    private String getPermissions(File file) {
        return (file.isDirectory() ? "d" : "-")
                + (file.canRead() ? "r" : "-")
                + (file.canWrite() ? "w" : "-")
                + (file.canExecute() ? "x" : "-");
    }

    private String getFileCreatedAt(File file) {
        BasicFileAttributeView faView = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            BasicFileAttributes attributes = faView.readAttributes();
            FileTime fileTime = attributes.creationTime();

            return sdf.format(new Date(fileTime.toMillis()));
        } catch (IOException e) {
            return sdf.format(new Date(0));
        }
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
