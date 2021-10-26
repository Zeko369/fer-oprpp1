package hr.fer.oprpp1.custom.scripting.node;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.collections.Processor;

/**
 * Base class used to represent all Nodes
 *
 * @author franzekan
 */
public abstract class Node {
    /**
     * Node children
     */
    private final ArrayIndexedCollection children;

    /**
     * Instantiates a new Node.
     */
    public Node() {
        this.children = new ArrayIndexedCollection();
    }

    /**
     * Add child node.
     *
     * @param child the child
     */
    public void addChildNode(Node child) {
        this.children.add(child);
    }

    /**
     * Number of children
     *
     * @return the number of children
     */
    public int numberOfChildren() {
        return this.children.size();
    }

    /**
     * Gets child by index
     *
     * @param index the index
     * @return the child
     */
    public Node getChild(int index) {
        return (Node) this.children.get(index);
    }

    /**
     * To code string.
     *
     * @return the string
     */
    public abstract String toCode();

    /**
     * To structure string.
     *
     * @param depth the depth
     * @return the string
     */
    public String toStructure(int depth) {
        return "";
    }

    /**
     * Helper for used to convert all the children to string
     *
     * @return the string
     */
    protected String childrenToString() {
        StringBuilder sb = new StringBuilder();
        this.forEachChildren(value -> sb.append(((Node) value).toCode()));

        return sb.toString();
    }

    /**
     * For each over all children
     *
     * @param p Processor instance
     */
    public void forEachChildren(Processor p) {
        this.children.forEach(p);
    }
}
