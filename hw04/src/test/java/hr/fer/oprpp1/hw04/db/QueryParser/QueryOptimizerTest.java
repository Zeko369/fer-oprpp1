package hr.fer.oprpp1.hw04.db.QueryParser;

import hr.fer.oprpp1.hw04.db.ComparisonOperators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryOptimizerTest {
    @Test
    void testDirectOnAComplexWithJMBAGQuery() {
        QueryParser parser = new QueryParser("jmbag = \"0123456789\" AND firstName = \"fooBar\"");
        assertTrue(parser.isDirectQuery());
    }

    @Test
    void testNever() {
        QueryParser parser = new QueryParser("jmbag = \"0123456789\" AND jmbag = \"fooBar\"");
        assertTrue(parser.isNever());

        assertEquals(1, parser.getQuery().size());
        assertEquals(ComparisonOperators.NEVER, parser.getQuery().get(0).getComparisonOperator());
    }
}
