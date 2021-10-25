package hr.fer.oprpp1.custom.scripting.elems;

/**
 * Element class used to store a function annotation
 * I.e. <code>@sin</code> will be parsed into this class
 */
public class ElementFunction extends Element {
    private final String name;

    /**
     * @param name the name
     */
    public ElementFunction(String name) {
        this.name = name;
    }

    /**
     * Name getter
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String asText() {
        return String.format("@%s", this.name);
    }
}
