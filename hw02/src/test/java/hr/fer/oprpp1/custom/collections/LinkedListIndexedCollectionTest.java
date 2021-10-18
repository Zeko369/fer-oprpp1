package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListIndexedCollectionTest {
    private LinkedListIndexedCollection list = null;

    @BeforeEach
    void setUp() {
        this.list = new LinkedListIndexedCollection();
        this.list.add("Hello");
    }

    @Test
    void constructorInitCollectionNull() {
        assertThrows(NullPointerException.class, () -> new LinkedListIndexedCollection(null));
    }

    @Test
    void constructorInitCollectionCopyElems() {
        LinkedListIndexedCollection tmp = new LinkedListIndexedCollection(this.list);
        assertEquals("Hello", tmp.get(0));
    }

    @Test
    void addAdds() {
        assertEquals(this.list.size(), 1);
        this.list.add("World");
        assertEquals(this.list.size(), 2);
    }

    @Test
    void addThrowsIfNullPassed() {
        assertThrows(NullPointerException.class, () -> this.list.add(null));
    }

    @Test
    void addResizes() {
        assertEquals(this.list.size(), 1);
        this.list.add("World");
        this.list.add("World");
        assertEquals(this.list.size(), 3);
    }

    @Test
    void get() {
        this.list.add("World");

        assertEquals("Hello", this.list.get(0));
        assertEquals("World", this.list.get(1));
    }

    @Test
    void toArray() {
        this.list.add("World");

        assertArrayEquals(new String[]{"Hello", "World"}, this.list.toArray());
    }


    @Test
    void getOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.list.get(10));
    }

    @Test
    void clear() {
        assertEquals(1, this.list.size());
        this.list.clear();

        assertEquals(0, this.list.size());
    }

    @Test
    void contains() {
        assertTrue(this.list.contains("Hello"));
        assertFalse(this.list.contains("Hello123"));
    }

    @Test
    void remove() {
        this.list.add("World");
        this.list.remove(1);

        assertEquals("Hello", this.list.get(0));
        assertEquals(1, this.list.size());
    }

    @Test
    void removeFirst() {
        this.list.add("World");
        this.list.remove(0);

        assertEquals("World", this.list.get(0));
        assertEquals(1, this.list.size());
    }

    @Test
    void removeOutOfRange() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.list.remove(-1));
    }

    @Test
    void removeByValue() {
        this.list.add("World");
        this.list.remove("World");

        assertEquals("Hello", this.list.get(0));
        assertEquals(1, this.list.size());
    }

    @Test
    void removeByValueFirst() {
        this.list.add("World");
        this.list.remove("Hello");

        assertEquals("World", this.list.get(0));
        assertEquals(1, this.list.size());
    }

    @Test
    void removeByValueNotFound() {
        this.list.add("World");
        assertFalse(this.list.remove("Foobar"));
    }
}
