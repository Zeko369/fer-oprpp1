package hr.fer.oprpp1.hw04.db;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import hr.fer.oprpp1.hw04.db.RecordFormatter.StudentRecordFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDBTest {
    private StudentDatabase sdb;

    private static final InputStream DEFAULT_STDIN = System.in;

    private static final PrintStream DEFAULT_STDOUT = System.out;
    private final ByteArrayOutputStream stdOutStream = new ByteArrayOutputStream();

    private static final PrintStream DEFAULT_STDERR = System.err;
    private final ByteArrayOutputStream stdErrStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        this.sdb = new StudentDatabase(List.of(
                "0001\tbar\tfoo\t1",
                "0002\tmestrovic\tpero\t2",
                "0003\tivic\tivan\t3",
                "0004\tbesto\tfran\t4"
        ));

        System.setOut(new PrintStream(this.stdOutStream));
        System.setErr(new PrintStream(this.stdErrStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(DEFAULT_STDIN);
        System.setOut(DEFAULT_STDOUT);
        System.setErr(DEFAULT_STDERR);
    }

    // EXAM
    @Test
    void testWithStatistics() throws Exception {
        System.setIn(new ByteArrayInputStream("query jmbag = \"0000000001\" with-statistics\n".getBytes()));

        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{}));
        assertEquals(0, status);

        assertStdOut("Welcome to the student database.\n" +
                "> Using index for record retrieval.\n" +
                "+============+===========+=======+===+\n" +
                "| 0000000001 | Akšamović | Marin | 2 |\n" +
                "+============+===========+=======+===+\n" +
                "Records selected: 1\n" +
                "Average grade is 2.00\n" +
                "Grades:\n" +
                "1: 0\n" +
                "2: 1\n" +
                "3: 0\n" +
                "4: 0\n" +
                "5: 0\n" +
                "> Exiting...");
    }

    @Test
    void testRunQueryNoQueryKeyword() {
        StudentDB.runQuery("firstName = \"notFound\"", this.sdb);
        assertEquals("Invalid command, a query needs to start with `query `", stdOutStream.toString().trim());
    }

    @Test
    void testRunQueryNotFound() {
        StudentDB.runQuery("query firstName = \"notFound\"", this.sdb);
        assertEquals("Records selected: 0", stdOutStream.toString().trim());
    }

    private void assertStdOut(String output) {
        assertEquals(output, stdOutStream.toString().trim());
    }

    private void assertStdErr(String output) {
        assertEquals(output, stdErrStream.toString().trim());
    }

    private String getFormatted(List<StudentRecord> records) {
        return StudentRecordFormatter.format(records)
                .stream()
                .reduce("", (a, b) -> a + b + "\n")
                .trim();
    }

    @Test
    void testRunWorksWithTabs() {
        StudentDB.runQuery("query\t  \tjmbag\t=\t\"0001\"", this.sdb);

        assertStdOut("Using index for record retrieval.\n" + this.getFormatted(List.of(this.sdb.all().get(0))));
    }

    @Test
    void testRunMultipleJMBAGButOneOfThemIsDirect() {
        StudentDB.runQuery("query jmbag <= \"0001\" AND jmbag = \"0001\"", this.sdb);

        assertStdOut("Using index for record retrieval.\n" + this.getFormatted(List.of(this.sdb.all().get(0))));
    }

    @Test
    void testRunQueryIndexed() {
        StudentDB.runQuery("query jmbag = \"0001\"", this.sdb);

        assertStdOut("Using index for record retrieval.\n" + this.getFormatted(List.of(this.sdb.all().get(0))));
    }

    @Test
    void testRunQueryIndexedButNotFound() {
        StudentDB.runQuery("query jmbag = \"0034\"", this.sdb);

        assertStdOut("Using index for record retrieval.\n" + this.getFormatted(List.of()));
    }

    @Test
    void testRunQueryNever() {
        StudentDB.runQuery("query jmbag = \"0034\" AND jmbag = \"0234\"", this.sdb);

        assertStdErr("This query will always return 0 rows");
        assertStdOut(this.getFormatted(List.of()));
    }

    @Test
    void testRunQueryWrongQuery() {
        StudentDB.runQuery("query j m bag = AND jmbag = \"0234\"", this.sdb);

        assertStdOut("Error parsing query.");
    }

    @Test
    void testMain() throws Exception {
        System.setIn(new ByteArrayInputStream("query jmbag = \"0000000001\"\nexit\n".getBytes()));

        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{}));
        assertEquals(0, status);

        assertStdOut("Welcome to the student database.\n" +
                "> Using index for record retrieval.\n" +
                "+============+===========+=======+===+\n" +
                "| 0000000001 | Akšamović | Marin | 2 |\n" +
                "+============+===========+=======+===+\n" +
                "Records selected: 1\n" +
                "> Goodbye!");
    }

    @Test
    void testMainNoExit() throws Exception {
        System.setIn(new ByteArrayInputStream("query jmbag = \"0000000001\"\n".getBytes()));

        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{}));
        assertEquals(0, status);

        assertStdOut("Welcome to the student database.\n" +
                "> Using index for record retrieval.\n" +
                "+============+===========+=======+===+\n" +
                "| 0000000001 | Akšamović | Marin | 2 |\n" +
                "+============+===========+=======+===+\n" +
                "Records selected: 1\n" +
                "> Exiting...");
    }

    @Test
    void testMainWrongFile() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{"notFound.txt"}));

        assertEquals(1, status);
        assertStdErr("Error reading database file.");
    }

    @Test
    void testForErrorParsingMessage() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{"./src/test/resources/parse_error.txt"}));

        assertEquals(1, status);
        assertStdErr("Error parsing a row in student database, Wrong number of elements");
    }

    @Test
    void testForErrorParsingMessageMultiple() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{"./src/test/resources/same_jmbag.txt"}));

        assertEquals(1, status);
        assertStdErr("Error parsing database, multiple records with same JMBAG");
    }

    @Test
    void testForErrorParsingNullPassed() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{null}));

        assertEquals(1, status);
        assertStdErr("Database file can't be null");
    }
}
