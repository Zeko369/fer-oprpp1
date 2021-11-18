package hr.fer.oprpp1.hw04.db;

import hr.fer.oprpp1.hw04.db.QueryParser.QueryParser;
import hr.fer.oprpp1.hw04.db.QueryParser.QueryParserException;
import hr.fer.oprpp1.hw04.db.RecordFormatter.StudentRecordFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class StudentDB {
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("./database.txt"));
        } catch (IOException e) {
            System.err.println("Error reading database file.");
            System.exit(1);
        }

        StudentDatabase sdb = new StudentDatabase(lines);

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();

            if(input.equals("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            try {
                if(!input.startsWith("query ")) {
                    System.out.println("Invalid command, a query needs to start with `query `");
                    continue;
                }

                QueryParser parser = new QueryParser(input.substring(6));
                if (parser.isDirectQuery()) {
                    StudentRecord student = sdb.forJMBAG(parser.getQueriedJMBAG());

                    System.out.println("Using index for record retrieval.");
                    StudentRecordFormatter.format(student).forEach(System.out::println);
                    continue;
                }

                StudentRecordFormatter.format(sdb.filter(parser.getFilter())).forEach(System.out::println);
            } catch (QueryParserException e) {
                System.out.println("Error parsing query.");
            }
        }

//        try {
//
//            System.out.println(sdb.forJMBAG("0000000010"));
//
//            List<String> formattedOut = StudentRecordFormatter.format(sdb.filter(s -> s.getFirstName().equals("Ivan")));
//            for (String line : formattedOut) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading database.txt");
//        }
    }
}
