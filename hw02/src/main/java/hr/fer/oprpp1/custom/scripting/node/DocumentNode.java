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

    public String toStructure() {
        StringBuilder sb = new StringBuilder();
        sb.append("ROOT:\n");
        this.forEachChildren((c) -> {
            sb.append(((Node) c).toStructure(2));
            sb.append("\n");
        });

        return sb.toString();
    }
}
