package hr.fer.oprpp1.custom.scripting.node;

public class DocumentNode extends Node {
    @Override
    public String toString() {
        return this.toCode();
    }

    @Override
    public String toCode() {
        return this.childrenToString();
    }
}
