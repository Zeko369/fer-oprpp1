package hr.fer.oprpp1.custom.scripting.elems;

/**
 * Element class used to store int value
 * I.e. <code>123</code> will be parsed into this class
 */
public class ElementConstantInteger extends Element {
    private final int value;

    /**
     * @param value number
     */
    public ElementConstantInteger(int value) {
        this.value = value;
    }

    /**
     * Value getter
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    @Override
    public String asText() {
        return String.valueOf(this.value);
    }

}
