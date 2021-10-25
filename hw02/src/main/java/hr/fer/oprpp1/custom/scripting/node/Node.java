package hr.fer.oprpp1.custom.scripting.node;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.collections.Processor;

public abstract class Node {
    private final ArrayIndexedCollection children;

    public Node() {
        this.children = new ArrayIndexedCollection();
    }

    public void addChildNode(Node child) {
        this.children.add(child);
    }

    public int numberOfChildren() {
        return this.children.size();
    }

    public Node getChild(int index) {
        return (Node) this.children.get(index);
    }

    public void forEachChildren(Processor p) {
        this.children.forEach(p);
    }
}
