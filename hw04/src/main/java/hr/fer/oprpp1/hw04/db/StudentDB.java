package hr.fer.oprpp1.hw04.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StudentDB {
    public static void main(String[] args) {
        // Read all lines from database.txt into a list of strings.

        try {
            List<String> lines = Files.readAllLines(Path.of("./database.txt"));
            StudentDatabase sdb = new StudentDatabase(lines);

            System.out.println(sdb.forJMBAG("0000000010"));

            List<String> formattedOut = RecordFormatter.format(sdb.filter(s -> s.getFirstName().equals("Ivan")));
            for (String line : formattedOut) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading database.txt");
        }
    }
}
