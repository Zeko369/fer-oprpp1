package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRecordTest {
    @Test
    void testFromTSV() {
        StudentRecord sr = StudentRecord.fromTSVLine("0123\tbar\tfoo\t1");
        assertEquals("0123", sr.getJmbag());
        assertEquals("foo", sr.getFirstName());
        assertEquals("bar", sr.getLastName());
        assertEquals(1, sr.getFinalGrade());
    }

    @Test
    void testFromTSVInvalidGrade() {
        assertThrows(StudentRecordParseException.class, () -> StudentRecord.fromTSVLine("0123\tbar\tfoo\t-1"));
        assertThrows(StudentRecordParseException.class, () -> StudentRecord.fromTSVLine("0123\tbar\tfoo\t10000000000000"));
        assertThrows(StudentRecordParseException.class, () -> StudentRecord.fromTSVLine("0123\tbar\tfoo\t10"));
    }

    @Test
    void testFromTSVInvalidNumberOfElems() {
        assertThrows(StudentRecordParseException.class, () -> StudentRecord.fromTSVLine("0123\tbar\tfoo"));
        assertThrows(StudentRecordParseException.class, () -> StudentRecord.fromTSVLine("0123\tbar\tfoo\t3\tfoobar"));
    }

    @Test
    void testEquals() {
        StudentRecord sr1 = StudentRecord.fromTSVLine("0123\tbar\tfoo\t1");
        StudentRecord sr2 = StudentRecord.fromTSVLine("0123\tfoo\tbar\t1");
        StudentRecord sr3 = StudentRecord.fromTSVLine("0124\tbar\tfoo\t1");

        assertEquals(sr1, sr2);
        assertNotEquals(sr1, sr3);
        assertFalse(sr2.equals("Hello"));
    }

    @Test
    void testHashCode() {
        StudentRecord sr1 = StudentRecord.fromTSVLine("0123\tbar\tfoo\t1");
        assertDoesNotThrow(sr1::hashCode);
    }

    @Test
    void testToString() {
        StudentRecord sr = StudentRecord.fromTSVLine("0123\tbar\tfoo\t1");

        assertEquals("STUDENT: [0123 foo bar 1]", sr.toString());
    }
}
