package hr.fer.oprpp1.custom.scripting.elems;

/**
 * Element class used to store an operator <code>+/-*^</code>
 * I.e. <code>+</code> will be parsed into this class
 */
public class ElementOperator extends Element {
    private final String symbol;

    /**
     * @param symbol the symbol
     */
    public ElementOperator(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Symbol getter
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String asText() {
        return this.symbol;
    }
}
