package hr.fer.oprpp1.hw04.db;

import hr.fer.oprpp1.hw04.db.QueryParser.QueryParser;
import hr.fer.oprpp1.hw04.db.QueryParser.QueryParserException;
import hr.fer.oprpp1.hw04.db.RecordFormatter.StudentRecordFormatter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Simple application that loads students from a file and allows users to query them from a console.
 *
 * @author franzekan
 */
public class StudentDB {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String filename = "./src/main/resources/database.txt";
        if (args.length == 1) {
            filename = args[0];
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(Objects.requireNonNull(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading database file.");
            System.exit(1);
            return;
        } catch (NullPointerException e) {
            System.err.println("Database file can't be null");
            System.exit(1);
            return;
        }

        StudentDatabase sdb;
        try {
            sdb = new StudentDatabase(lines);
        } catch (StudentDatabaseUniqueKeyException e) {
            System.err.println("Error parsing database, multiple records with same JMBAG");
            System.exit(1);
            return;
        } catch (StudentRecordParseException e) {
            System.err.printf("Error parsing a row in student database, %s", e.getMessage());
            System.exit(1);
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the student database.");

        String input;
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                System.out.println("Exiting...");
                System.exit(0);
            }

            input = scanner.nextLine();

            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
                return;
            }

            runQuery(input, sdb);
        }
    }

    /**
     * Run query.
     *
     * @param query the query
     * @param sdb   the student database
     */
    protected static void runQuery(String query, StudentDatabase sdb) {
        try {
            if (!query.startsWith("query")) {
                System.out.println("Invalid command, a query needs to start with `query `");
                return;
            }

            QueryParser parser = new QueryParser(query.substring(5).trim());
            if (parser.isNever()) {
                System.err.println("This query will always return 0 rows");
            }

            List<StudentRecord> filtered;
            if (parser.isDirectQuery()) {
                System.out.println("Using index for record retrieval.");

                StudentRecord student = sdb.forJMBAG(parser.getQueriedJMBAG());
                if (student == null || !parser.getFilter().accepts(student)) {
                    filtered = List.of();
                } else {
                    filtered = List.of(student);
                }
            } else {
                filtered = sdb.filter(parser.getFilter());
            }

            if (parser.getOrderBy().size() > 0) {
                List<String> orderBy = parser.getOrderBy();
                filtered.sort((o1, o2) -> {
                    for (String s : orderBy) {
                        String s1 = FieldValueGetters.getByName(s).get(o1);
                        String s2 = FieldValueGetters.getByName(s).get(o2);

                        if (s1.equals(s2)) {
                            continue;
                        }

                        return s1.compareTo(s2);
                    }

                    return 0;
                });
            }

            StudentRecordFormatter.format(filtered).forEach(System.out::println);

            if (parser.getWithStatistic()) {
                double average = 0;
                int[] grades = new int[5];

                for (StudentRecord student : filtered) {
                    average += student.getFinalGrade();
                    grades[student.getFinalGrade() - 1]++;
                }

                System.out.printf("Average grade is %.2f\n", average / filtered.size());
                System.out.println("Grades:");
                for (int i = 0; i < grades.length; i++) {
                    System.out.printf("%d: %d%n", i + 1, grades[i]);
                }
            }
        } catch (QueryParserException e) {
            System.out.println("Error parsing query.");
        }
    }
}
