package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonOperatorsTest {
    @Test
    void lessOperatorTest() {
        this.operatorTestCase(ComparisonOperators.LESS, new boolean[]{true, false, false});
    }

    @Test
    void lessOperatorNotNullTest() {
        this.failForNull(ComparisonOperators.LESS);
    }

    @Test
    void lessOrEqualOperatorTest() {
        this.operatorTestCase(ComparisonOperators.LESS_OR_EQUALS, new boolean[]{true, true, false});
    }

    @Test
    void lessOrEqualOperatorNotNullTest() {
        this.failForNull(ComparisonOperators.LESS_OR_EQUALS);
    }

    @Test
    void equalOperatorTest() {
        this.operatorTestCase(ComparisonOperators.EQUALS, new boolean[]{false, true, false});
    }

    @Test
    void equalOperatorNotNullTest() {
        this.failForNull(ComparisonOperators.EQUALS);
    }

    @Test
    void greaterOrEqualOperatorTest() {
        this.operatorTestCase(ComparisonOperators.GREATER_OR_EQUALS, new boolean[]{false, true, true});
    }

    @Test
    void greaterOrEqualOperatorNotNullTest() {
        this.failForNull(ComparisonOperators.GREATER_OR_EQUALS);
    }

    @Test
    void greaterOperatorTest() {
        this.operatorTestCase(ComparisonOperators.GREATER, new boolean[]{false, false, true});
    }

    @Test
    void greaterOperatorNotNullTest() {
        this.failForNull(ComparisonOperators.GREATER);
    }

    @Test
    void notEqualOperatorTest() {
        this.operatorTestCase(ComparisonOperators.NOT_EQUALS, new boolean[]{true, false, true});
    }

    @Test
    void notEqualOperatorNotNullTest() {
        this.failForNull(ComparisonOperators.NOT_EQUALS);
    }

    @Test
    void likeWithoutAsteriskOperatorTest() {
        assertTrue(ComparisonOperators.LIKE.satisfied("abc", "abc"));
        assertFalse(ComparisonOperators.LIKE.satisfied("bdc", "abc"));
    }

    @Test
    void likeWithAsteriskOperatorTest() {
        assertFalse(ComparisonOperators.LIKE.satisfied("Zagreb", "Aba*"));
        assertFalse(ComparisonOperators.LIKE.satisfied("AAA", "AA*AA")); // Because of S1 length

        assertTrue(ComparisonOperators.LIKE.satisfied("AAAA", "AA*AA"));
        assertTrue(ComparisonOperators.LIKE.satisfied("AAAAAA", "AA*AA"));
        assertTrue(ComparisonOperators.LIKE.satisfied("FRAN", "*AN"));
        assertTrue(ComparisonOperators.LIKE.satisfied("FRAN", "FR*"));
    }

    @Test
    void likeWithTooManyAsteriskOperatorTest() {
        assertThrows(ComparisonOperatorException.class, () -> ComparisonOperators.LIKE.satisfied("AAAAAA", "AA***AA"));
    }

    @Test
    void likeValueHasAsteriskOperatorTest() {
        assertThrows(ComparisonOperatorException.class, () -> ComparisonOperators.LIKE.satisfied("AA*A", "A*AA"));
    }

    private void operatorTestCase(IComparisonOperator operator, boolean[] expected) {
        assertEquals(expected[0], operator.satisfied("1", "2"));
        assertEquals(expected[1], operator.satisfied("2", "2"));
        assertEquals(expected[2], operator.satisfied("3", "2"));
    }

    private void failForNull(IComparisonOperator operator) {
        assertThrows(NullPointerException.class, () -> operator.satisfied(null, "2"));
        assertThrows(NullPointerException.class, () -> operator.satisfied("2", null));
        assertThrows(NullPointerException.class, () -> operator.satisfied(null, null));
    }

}

