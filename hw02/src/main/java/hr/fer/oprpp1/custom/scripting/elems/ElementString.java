package hr.fer.oprpp1.custom.scripting.elems;

/**
 * Element class used to store a string literal
 * I.e. <code>"FooBar"</code> will be parsed into this class
 */
public class ElementString extends Element {
    private final String value;

    /**
     * @param value the value
     */
    public ElementString(String value) {
        this.value = value;
    }

    /**
     * Value getter
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String asText() {
        return String.format("\"%s\"", this.value);
    }
}
