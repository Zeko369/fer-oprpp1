package hr.fer.oprpp1.custom.scripting.elems;

/**
 * Element class used to store double value
 * I.e. <code>123.5</code> will be parsed into this class
 *
 * @author franzekan
 */
public class ElementConstantDouble extends Element {
    private final double value;

    /**
     * Instantiates a new Element constant double.
     *
     * @param value number value
     */
    public ElementConstantDouble(double value) {
        this.value = value;
    }

    /**
     * Value getter
     *
     * @return value value
     */
    public double getValue() {
        return this.value;
    }

    @Override
    public String asText() {
        return String.valueOf(this.value);
    }
}
