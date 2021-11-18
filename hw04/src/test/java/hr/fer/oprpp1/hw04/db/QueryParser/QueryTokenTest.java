package hr.fer.oprpp1.hw04.db.QueryParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTokenTest {
    @Test
    void testToString() {
        QueryToken token = new QueryToken(QueryTokenType.VALUE, "AND");
        assertEquals("VALUE AND", token.toString());
    }
}
