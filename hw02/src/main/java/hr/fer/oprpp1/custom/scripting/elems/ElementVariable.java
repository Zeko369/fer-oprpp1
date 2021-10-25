package hr.fer.oprpp1.custom.scripting.elems;

/**
 * Element class used to store a variable
 * I.e. <code>foo</code> will be parsed into this class
 */
public class ElementVariable extends Element {
    private final String name;

    /**
     * @param name the name
     */
    public ElementVariable(String name) {
        this.name = name;
    }

    @Override
    public String asText() {
        return this.name;
    }

    /**
     * Name getter
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
