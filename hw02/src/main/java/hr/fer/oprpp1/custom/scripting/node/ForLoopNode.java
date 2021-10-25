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
    public String toCode() {
        StringBuilder sb = new StringBuilder();
        String step = this.stepExpression == null ? "" : this.stepExpression.asText();

        sb.append(String.format("{$ FOR %s %s %s %s $}", this.variable.asText(), this.startExpression.asText(), this.endExpression.asText(), step));
        sb.append(this.childrenToString());
        sb.append("{$ END $}");

        return sb.toString();
    }

    @Override
    public String toStructure(int depth) {
        StringBuilder sb = new StringBuilder();
        String step = this.stepExpression == null ? "" : this.stepExpression.asText();

        sb.append(String.format("%sFOR: %s %s %s %s\n", " ".repeat(depth), this.variable.asText(), this.startExpression.asText(), this.endExpression.asText(), step));
        this.forEachChildren(c -> sb.append(((Node) c).toStructure(depth + 2)));

        return sb.toString();
    }
}
