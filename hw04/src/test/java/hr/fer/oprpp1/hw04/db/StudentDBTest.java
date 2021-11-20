package hr.fer.oprpp1.hw04.db;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDBTest {
    private StudentDatabase sdb;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream stdOutStream = new ByteArrayOutputStream();

    private final PrintStream standardErr = System.err;
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

    private static final InputStream DEFAULT_STDIN = System.in;

    @AfterEach
    void tearDown() {
        System.setIn(DEFAULT_STDIN);
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

    @Test
    void testRunQueryIndexed() {
        StudentDB.runQuery("query jmbag = \"0001\"", this.sdb);
        assertEquals(
                "Using index for record retrieval.\n" +
                        "+======+=====+=====+===+\n" +
                        "| 0001 | bar | foo | 1 |\n" +
                        "+======+=====+=====+===+\n" +
                        "Records selected: 1", stdOutStream.toString().trim());
    }

    @Test
    void testRunQueryIndexedButNotFound() {
        StudentDB.runQuery("query jmbag = \"0034\"", this.sdb);
        assertEquals(
                "Using index for record retrieval.\n" +
                        "Records selected: 0", stdOutStream.toString().trim());
    }

    @Test
    void testRunQueryNever() {
        StudentDB.runQuery("query jmbag = \"0034\" AND jmbag = \"0234\"", this.sdb);
        assertEquals("This query will always return 0 rows", stdErrStream.toString().trim());
        assertEquals("Records selected: 0", stdOutStream.toString().trim());
    }

    @Test
    void testRunQueryWrongQuery() {
        StudentDB.runQuery("query j m bag = AND jmbag = \"0234\"", this.sdb);
        assertEquals("Error parsing query.", stdOutStream.toString().trim());
    }

    @Test
    void testMain() throws Exception {
        System.setIn(new ByteArrayInputStream("query jmbag = \"0000000001\"\nexit\n".getBytes()));

        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{}));
        assertEquals(0, status);

        assertEquals("Welcome to the student database.\n" +
                "> Using index for record retrieval.\n" +
                "+============+===========+=======+===+\n" +
                "| 0000000001 | Akšamović | Marin | 2 |\n" +
                "+============+===========+=======+===+\n" +
                "Records selected: 1\n" +
                "> Goodbye!", stdOutStream.toString().trim());
    }

    @Test
    void testMainNoExit() throws Exception {
        System.setIn(new ByteArrayInputStream("query jmbag = \"0000000001\"\n".getBytes()));

        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{}));
        assertEquals(0, status);

        assertEquals("Welcome to the student database.\n" +
                "> Using index for record retrieval.\n" +
                "+============+===========+=======+===+\n" +
                "| 0000000001 | Akšamović | Marin | 2 |\n" +
                "+============+===========+=======+===+\n" +
                "Records selected: 1\n" +
                "> Exiting...", stdOutStream.toString().trim());
    }

    @Test
    void testMainWrongFile() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> StudentDB.main(new String[]{"notFound.txt"}));

        assertEquals(1, status);
        assertEquals("Error reading database file.", stdErrStream.toString().trim());
    }
}
