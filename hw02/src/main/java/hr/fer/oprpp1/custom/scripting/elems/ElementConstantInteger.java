package hr.fer.oprpp1.custom.scripting.elems;

public class ElementConstantInteger extends Element {
    private final int value;

    public ElementConstantInteger(int value) {
        this.value = value;
    }


    @Override
    public String asText() {
        return String.valueOf(this.value);
    }

    public int getValue() {
        return value;
    }
}
