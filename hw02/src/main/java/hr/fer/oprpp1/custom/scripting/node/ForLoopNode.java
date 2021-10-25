package hr.fer.oprpp1.custom.scripting.node;

import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;

public class ForLoopNode extends Node {
    private final ElementVariable variable;
    private final Element startExpression;
    private final Element endExpression;
    private final Element stepExpression;

    public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression, Element stepExpression) {
        this.variable = variable;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.stepExpression = stepExpression;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String step = this.stepExpression == null ? "" : this.stepExpression.asText();
        sb.append(String.format("{$ FOR: %s %s %s %s $}\n", this.variable.asText(), this.startExpression.asText(), this.endExpression.asText(), step));

        // TODO: Merge with other
        this.forEachChildren(value -> {
            sb.append(value.toString());
            sb.append("\n");
        });

        sb.append("{$ END $}");

        return sb.toString();
    }
}
