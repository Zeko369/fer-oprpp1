package hr.fer.oprpp1.custom.scripting.node;

import hr.fer.oprpp1.custom.scripting.elems.Element;

public class EchoNode extends Node {
    private final Element[] elements;

    public EchoNode(Element[] elements) {
        this.elements = elements;
    }
}
