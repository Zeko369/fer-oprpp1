package hr.fer.oprpp1.hw04.db.QueryParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryLexerTest {
    // exam
    @Test
    void testWithStatistics() {
        QueryLexer lexer = new QueryLexer("with-statistics");
        assertEquals(QueryTokenType.OPTION, lexer.getNextToken().getType());
        assertEquals("with-statistics", lexer.getToken().getValue());
    }

    @Test
    void testEmptyQuery() {
        QueryLexer lexer = new QueryLexer("");
        assertEquals(QueryTokenType.EOF, lexer.getNextToken().getType());
    }

    @Test
    void testSimpleQuery() {
        QueryLexer lexer = new QueryLexer("firstName = \"fooBar\"");
        assertEquals(QueryTokenType.COLUMN, lexer.getNextToken().getType());
        assertEquals("firstName", lexer.getToken().getValue());

        assertEquals(QueryTokenType.OPERATOR, lexer.getNextToken().getType());
        assertEquals("=", lexer.getToken().getValue());

        assertEquals(QueryTokenType.VALUE, lexer.getNextToken().getType());
        assertEquals("fooBar", lexer.getToken().getValue());
    }

    @Test
    void testComplexQuery() {
        QueryLexer lexer = new QueryLexer("firstName = \"fooBar\" AnD lastName = \"fooBar123\"");
        assertEquals(QueryTokenType.COLUMN, lexer.getNextToken().getType());
        assertEquals("firstName", lexer.getToken().getValue());

        assertEquals(QueryTokenType.OPERATOR, lexer.getNextToken().getType());
        assertEquals("=", lexer.getToken().getValue());

        assertEquals(QueryTokenType.VALUE, lexer.getNextToken().getType());
        assertEquals("fooBar", lexer.getToken().getValue());

        assertEquals(QueryTokenType.LOGICAL_OPERATOR, lexer.getNextToken().getType());
        assertEquals("AND", lexer.getToken().getValue());

        assertEquals(QueryTokenType.COLUMN, lexer.getNextToken().getType());
        assertEquals("lastName", lexer.getToken().getValue());

        assertEquals(QueryTokenType.OPERATOR, lexer.getNextToken().getType());
        assertEquals("=", lexer.getToken().getValue());

        assertEquals(QueryTokenType.VALUE, lexer.getNextToken().getType());
        assertEquals("fooBar123", lexer.getToken().getValue());
    }

    @Test
    void testWithTabs() {
        QueryLexer lexer = new QueryLexer("\tfirstName\t=\t\"fooBar\"");

        assertEquals(QueryTokenType.COLUMN, lexer.getNextToken().getType());
        assertEquals("firstName", lexer.getToken().getValue());

        assertEquals(QueryTokenType.OPERATOR, lexer.getNextToken().getType());
        assertEquals("=", lexer.getToken().getValue());

        assertEquals(QueryTokenType.VALUE, lexer.getNextToken().getType());
        assertEquals("fooBar", lexer.getToken().getValue());
    }
}
