package hr.fer.oprpp1.custom.scripting.node;

public class DocumentNode extends Node {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.forEachChildren(value -> {
            sb.append(value.toString());
            sb.append("\n");
        });

        return sb.toString();
    }
}
