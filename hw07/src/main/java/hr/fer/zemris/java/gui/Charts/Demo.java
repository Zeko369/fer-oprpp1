package hr.fer.zemris.java.gui.Charts;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Demo extends JFrame {
    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.err.println("Usage: java Demo <path to data file>");
            System.exit(1);
        }

        List<String> lines = Files.readAllLines(Paths.get(args[0]));

        if(lines.size() != 6) {
            System.err.println("Expected 6 lines in data file");
            System.exit(1);
        }
    }

    public Demo() {

    }
}
