package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDatabaseTest {
    private StudentDatabase db;

    @BeforeEach
    void setupDB() {
        this.db = new StudentDatabase(List.of("0001\tbar\tfoo1\t4", "0002\tbar\tfoo2\t4", "0003\tbar\tfoo3\t4"));
    }


    @Test
    void loadDataFromString() {
        StudentDatabase sb = new StudentDatabase(List.of("0001\tbar\tfoo\t4"));
        assertEquals(1, sb.size());
    }

    @Test
    void throwsForSameJMBAG() {
        assertThrows(StudentDatabaseUniqueKeyException.class, () ->
                new StudentDatabase(List.of("0001\tbar\tfoo\t4", "0001\tbar\tfoo\t4"))
        );
    }

    @Test
    void testGetByJMBAG() {
        assertEquals("foo1", db.forJMBAG("0001").getFirstName());
    }

    @Test
    void testGetByJMBAGThrowsNullPointerEx() {
        assertNull(db.forJMBAG("1234"));
    }

    @Test
    void testFilter() {
        List<StudentRecord> filtered = db.filter(s -> s.getFirstName().equals("foo1"));
        assertEquals(1, filtered.size());
    }
}
