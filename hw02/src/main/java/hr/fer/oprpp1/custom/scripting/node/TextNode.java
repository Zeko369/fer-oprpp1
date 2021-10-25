package hr.fer.oprpp1.custom.scripting.node;

public class TextNode extends Node {
    private final String text;

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public String toCode() {
        return this.text;
    }

    @Override
    public String toStructure(int depth) {
        return String.format("%sTEXT: %s\n", " ".repeat(depth), this.text.replace("\n", "\\n"));
    }
}
