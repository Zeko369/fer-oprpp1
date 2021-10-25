package hr.fer.oprpp1.custom.scripting.node;

import hr.fer.oprpp1.custom.scripting.elems.Element;

public class EchoNode extends Node {
    private final Element[] elements;

    public EchoNode(Element[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{$= ");

        for (Element element : this.elements) {
            sb.append(element.asText());
            sb.append(" ");
        }

        sb.append("$}");

        return sb.toString();
    }
}
