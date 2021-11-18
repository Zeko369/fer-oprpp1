package hr.fer.oprpp1.hw04.db.QueryParser;

public class QueryToken {
    private final QueryTokenType type;
    private final String value;

    public QueryToken(QueryTokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean isEOF()  {
        return type == QueryTokenType.EOF;
    }

    public QueryTokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + " " + value;
    }
}
