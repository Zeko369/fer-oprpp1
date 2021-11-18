package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FieldValueGetterTest {
    @Test
    public void testGetFieldValue() {
        Map<String, IFieldValueGetter> tmp = new HashMap<>();
        tmp.put("JMBAG", FieldValueGetters.JMBAG);
        tmp.put("FIRST_NAME", FieldValueGetters.FIRST_NAME);
        tmp.put("LAST_NAME", FieldValueGetters.LAST_NAME);

        for (String field : tmp.keySet()) {
            assertEquals(FieldValueGetters.getByName(field), tmp.get(field));
        }
    }

    @Test
    public void testThrowForWrongKey() {
        assertThrows(IllegalArgumentException.class, () -> FieldValueGetters.getByName("WRONG_KEY"));
    }
}
