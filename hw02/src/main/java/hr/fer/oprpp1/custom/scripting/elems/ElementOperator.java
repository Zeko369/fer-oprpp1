package hr.fer.oprpp1.custom.scripting.elems;

public class ElementOperator extends Element {
    private final String symbol;

    public ElementOperator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String asText() {
        return this.symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
