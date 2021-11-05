package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleHashtableTest {
    private SimpleHashtable<String, String> dict = null;

    @BeforeEach
    void setUp() {
        this.dict = new SimpleHashtable<>(1);
        this.dict.put("foo", "Value1");
        this.dict.put("bar", "Value2");
    }

    @Test
    void size() {
        for(SimpleHashtable.TableEntry<String, String> entry : this.dict.toArray()) {
            System.out.printf("%s %s\n", entry.getKey(), entry.getValue());
        }

        assertEquals(2, this.dict.size());
    }

    @Test
    void put() {
        this.dict.put("baz", "Value3");

        assertEquals(3, this.dict.size());
        assertEquals("Value3", this.dict.get("baz"));
    }

    @Test
    void putOverwrite() {
        assertEquals("Value1", this.dict.put("foo", "Value3"));
        assertEquals("Value3", this.dict.get("foo"));
    }

    @Test
    void get() {
        assertEquals("Value1", this.dict.get("foo"));
        assertNull(this.dict.get("baz"));
    }

    @Test
    void getWithNull() {
        assertNull(this.dict.get(null));
    }

    @Test
    void remove() {
        assertEquals(2, this.dict.size());
        assertEquals("Value1", this.dict.remove("foo"));
        assertEquals(1, this.dict.size());
    }

    @Test
    void testContainsKey() {
        assertTrue(this.dict.containsKey("foo"));
        assertFalse(this.dict.containsKey("baz"));
    }

    @Test
    void testContainsKeyNull() {
        assertFalse(this.dict.containsKey(null));
    }

    @Test
    void testContainsValue() {
        assertTrue(this.dict.containsValue("Value1"));
        assertFalse(this.dict.containsValue("foobar"));
    }

    @Test
    void testToArray() {
        SimpleHashtable.TableEntry<String, String>[] entries = this.dict.toArray();

        assertEquals(2, entries.length);
        assertEquals("Value1", entries[0].getValue());
        assertEquals("Value2", entries[1].getValue());

        assertEquals("foo", entries[0].getKey());
        assertEquals("bar", entries[1].getKey());
    }

    @Test
    void testToString() {
        assertEquals("[foo=Value1, bar=Value2]", this.dict.toString());
    }

    @Test
    void testIsEmpty() {
        assertFalse(this.dict.isEmpty());

        this.dict.remove("foo");
        this.dict.remove("bar");

        assertTrue(this.dict.isEmpty());
    }

    @Test
    void clear() {
        assertFalse(this.dict.isEmpty());

        this.dict.clear();

        assertTrue(this.dict.isEmpty());
    }
}
