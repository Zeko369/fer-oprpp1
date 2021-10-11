package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectStackTest {
    private ObjectStack stack = null;

    @BeforeEach
    void setUp() {
        this.stack = new ObjectStack();
        this.stack.push("Hello");
    }

    @Test
    void size() {
        assertEquals(1, this.stack.size());
        this.stack.push("World");
        assertEquals(2, this.stack.size());
    }

    @Test
    void isEmpty() {
        assertFalse(this.stack.isEmpty());
        this.stack.pop();
        assertTrue(this.stack.isEmpty());
    }

    @Test
    void push() {
        this.stack.push("World");
        assertEquals("World", this.stack.peek());
    }

    @Test
    void pop() {
        this.stack.push("World");
        assertEquals("World", this.stack.pop());
        assertEquals(1, this.stack.size());
    }

    @Test
    void peek() {
        assertEquals("Hello", this.stack.peek());
    }

    @Test
    void clear() {
        assertEquals("Hello", this.stack.peek());
        this.stack.clear();
        assertThrows(EmptyStackException.class, () -> this.stack.peek());
    }
}
