package hr.fer.oprpp1.hw04.db.QueryParser;

import hr.fer.oprpp1.hw04.db.ComparisonOperators;
import hr.fer.oprpp1.hw04.db.FieldValueGetters;
import hr.fer.oprpp1.hw04.db.IFilter;
import hr.fer.oprpp1.hw04.db.StudentRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryParserTest {
    @Test
    void testSimpleQuery() {
        QueryParser parser = new QueryParser("firstName = \"fooBar\"");

        assertEquals(1, parser.getQuery().size());
        assertEquals(FieldValueGetters.FIRST_NAME, parser.getQuery().get(0).getFieldGetter());
        assertEquals(ComparisonOperators.EQUALS, parser.getQuery().get(0).getComparisonOperator());
        assertEquals("fooBar", parser.getQuery().get(0).getStringLiteral());
    }

    @Test
    void testDirectQuery() {
        QueryParser parser = new QueryParser("jmbag = \"0123456789\"");

        assertEquals(1, parser.getQuery().size());
        assertTrue(parser.isDirectQuery());
        assertEquals("0123456789", parser.getQueriedJMBAG());
    }

    @Test
    void testDirectOnAComplexQuery() {
        QueryParser parser = new QueryParser("lastName = \"0123456789\" AND firstName = \"fooBar\"");

        assertEquals(2, parser.getQuery().size());
        assertFalse(parser.isDirectQuery());
    }

    @Test
    void testDirectOnAComplexWithJMBAGQuery() {
        QueryParser parser = new QueryParser("jmbag = \"0123456789\" AND firstName = \"fooBar\"");

        assertEquals(2, parser.getQuery().size());
        assertTrue(parser.isDirectQuery());
    }

    @Test
    void testThrowOnDirectForNonDirectQueryQuery() {
        QueryParser parser = new QueryParser("firstName = \"0123456789\"");

        assertEquals(1, parser.getQuery().size());
        assertFalse(parser.isDirectQuery());
        assertThrows(QueryParserException.class, parser::getQueriedJMBAG);
    }

    @Test
    void testComplexQuery() {
        QueryParser parser = new QueryParser("firstName = \"fooBar\" AND lastName <= \"foo\"");

        assertEquals(2, parser.getQuery().size());
        assertEquals(FieldValueGetters.FIRST_NAME, parser.getQuery().get(0).getFieldGetter());
        assertEquals(ComparisonOperators.EQUALS, parser.getQuery().get(0).getComparisonOperator());
        assertEquals("fooBar", parser.getQuery().get(0).getStringLiteral());

        assertEquals(FieldValueGetters.LAST_NAME, parser.getQuery().get(1).getFieldGetter());
        assertEquals(ComparisonOperators.LESS_OR_EQUALS, parser.getQuery().get(1).getComparisonOperator());
        assertEquals("foo", parser.getQuery().get(1).getStringLiteral());
    }

    @Test
    void throwForWrongOrderOfQuery() {
        assertThrows(QueryParserException.class, () -> new QueryParser("<= fooBar firstName"));
        assertThrows(QueryParserException.class, () -> new QueryParser("firstName \"fooBar\" <="));
        assertThrows(QueryParserException.class, () -> new QueryParser("firstName <= and"));
        assertThrows(QueryParserException.class, () -> new QueryParser("AND firstName <= \"fooBar\""));
    }

    @Test
    void testIFilterReturn() {
        QueryParser parser = new QueryParser("firstName = \"fooBar\" AND lastName <= \"foo\"");
        IFilter filter = parser.getFilter();

        assertTrue(filter.accepts(new StudentRecord("0123456789", "fooBar", "foo", 5)));
        assertFalse(filter.accepts(new StudentRecord("0123456789", "notFooBar", "NotFoo", 5)));
    }

    @Test
    void testIsNever() {
        QueryParser parser = new QueryParser("jmbag = \"fooBar\" AND jmbag = \"foo\"");
        assertTrue(parser.isNever());
    }
}
