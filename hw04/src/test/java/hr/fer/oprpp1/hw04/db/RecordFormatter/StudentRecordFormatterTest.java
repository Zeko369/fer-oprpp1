package hr.fer.oprpp1.hw04.db.RecordFormatter;

import hr.fer.oprpp1.hw04.db.StudentRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRecordFormatterTest {
    @Test
    void testFormattingOfEmptyArray() {
        List<String> result = StudentRecordFormatter.format(List.of());

        assertEquals(1, result.size());
        assertEquals("Records selected: 0", result.get(0));
    }

    @Test
    void testFormattingOfSingleRow() {
        List<String> result = StudentRecordFormatter.format(List.of(
                new StudentRecord("012345678", "foo", "bar", 5)
        ));

        assertEquals(4, result.size());
        assertEquals(result.get(0), result.get(2));
        assertEquals("| 012345678 | foo | bar | 5 |", result.get(1));
        assertEquals("Records selected: 1", result.get(3));
    }

    @Test
    void testCorrectOffsettingForMultiRow() {
        List<String> result = StudentRecordFormatter.format(List.of(
                new StudentRecord("012345678", "foo", "bar", 5),
                new StudentRecord("012345679", "foobar", "bar", 5)
        ));

        assertEquals(5, result.size());
        assertEquals(result.get(0), result.get(3));
        assertEquals("| 012345678 | foo    | bar | 5 |", result.get(1));
        assertEquals("| 012345679 | foobar | bar | 5 |", result.get(2));
        assertEquals("Records selected: 2", result.get(4));
    }
}

