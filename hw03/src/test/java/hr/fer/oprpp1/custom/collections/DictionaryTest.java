package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {
    private Dictionary<String, String> dict = null;

    @BeforeEach
    void setUp() {
        this.dict = new Dictionary<>();
        this.dict.put("foo", "Value1");
    }

    @Test
    void isEmpty() {
        assertFalse(this.dict.isEmpty());
        this.dict.remove("foo");
        assertTrue(this.dict.isEmpty());
    }

    @Test
    void size() {
        assertEquals(1, this.dict.size());
        this.dict.put("bar", "Value2");

        assertEquals(2, this.dict.size());

        this.dict.remove("foo");
        assertEquals(1, this.dict.size());
    }

    @Test
    void clear() {
        this.dict.clear();
        assertTrue(this.dict.isEmpty());
    }

    @Test
    void put() {
        this.dict.put("bar", "Value2");
        assertEquals("Value2", this.dict.get("bar"));
    }

    @Test
    void putOverwrite() {
        assertEquals("Value1", this.dict.put("foo", "Value2"));
        assertEquals("Value2", this.dict.get("foo"));
    }

    @Test
    void get() {
        assertEquals("Value1", this.dict.get("foo"));
    }

    @Test
    void remove() {
        this.dict.remove("foo");
        assertNull(this.dict.get("foo"));
    }

    @Test
    void removeNotFound() {
        assertNull(this.dict.remove("key_not_found"));
    }
}
