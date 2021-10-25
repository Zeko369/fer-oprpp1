package hr.fer.oprpp1.custom.scripting.node;

import hr.fer.oprpp1.custom.scripting.elems.Element;

public class EchoNode extends Node {
    private final Element[] elements;

    public EchoNode(Element[] elements) {
        this.elements = elements;
    }

    private String arguments() {
        StringBuilder sb = new StringBuilder();

        for (Element element : this.elements) {
            sb.append(element.asText());
            sb.append(" ");
        }

        return sb.toString();
    }

    @Override
    public String toCode() {
        return String.format("{$= %s $}", this.arguments());
    }

    @Override
    public String toStructure(int depth) {
        return String.format("%sECHO: %s", " ".repeat(depth), this.arguments().replace("\n", "\\n"));
    }
}
