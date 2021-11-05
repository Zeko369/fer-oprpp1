package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayIndexedCollectionTest {
    private ArrayIndexedCollection<String> arr = null;

    @BeforeEach
    void setUp() {
        this.arr = new ArrayIndexedCollection<>(2);
        this.arr.add("Hello");
    }

    @Test
    void constructorOutOfRangeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayIndexedCollection<>(-1));
    }

    @Test
    void constructorInitCollectionNull() {
        assertThrows(NullPointerException.class, () -> new ArrayIndexedCollection<>(null));
    }

    @Test
    void constructorInitCollectionCopyElems() {
        ArrayIndexedCollection<String> tmp = new ArrayIndexedCollection<>(this.arr);
        assertEquals("Hello", tmp.get(0));
    }

    @Test
    void addAdds() {
        assertEquals(this.arr.size(), 1);
        this.arr.add("World");
        assertEquals(this.arr.size(), 2);
    }

    @Test
    void addThrowsIfNullPassed() {
        assertThrows(NullPointerException.class, () -> this.arr.add(null));
    }

    @Test
    void addResizes() {
        assertEquals(this.arr.size(), 1);
        this.arr.add("World");
        this.arr.add("World");
        assertEquals(this.arr.size(), 3);
    }

    @Test
    void get() {
        assertEquals("Hello", this.arr.get(0));
    }


    @Test
    void getOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.arr.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.arr.get(10));
    }

    @Test
    void clear() {
        assertEquals(1, this.arr.size());
        this.arr.clear();

        assertEquals(0, this.arr.size());
    }

    @Test
    void insert() {
        this.arr.add("World");
        this.arr.insert("between", 1);

        assertArrayEquals(this.arr.toArray(), new String[]{"Hello", "between", "World"});
    }

    @Test
    void insertNullValue() {
        assertThrows(NullPointerException.class, () -> this.arr.insert(null, 0));
    }

    @Test
    void insertOutOfRangePosition() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.arr.insert("valid_string", -1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.arr.insert("valid_string", 10));
    }

    @Test
    void indexOfNotFound() {
        assertEquals(-1, this.arr.indexOf("Something"));
    }

    @Test
    void indexOf() {
        assertEquals(0, this.arr.indexOf("Hello"));
    }

    @Test
    void remove() {
        this.arr.add("World");
        this.arr.remove(0);

        assertEquals("World", this.arr.get(0));
        assertEquals(1, this.arr.size());
    }

    @Test
    void removeOutOfRange() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.arr.remove(-1));
    }

    @Test
    void contains() {
        assertTrue(this.arr.contains("Hello"));
        assertFalse(this.arr.contains("Hello123"));
    }

    @Test
    void containsOtherType() {
        assertFalse(this.arr.contains(123));
    }
}
