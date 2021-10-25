package hr.fer.oprpp1.custom.scripting.parser;

import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.node.DocumentNode;

public class SmartScriptParser {
    private final String body;
    private DocumentNode root;

    public SmartScriptParser(String body) {
        this.body = body;
        this.root = new DocumentNode();

        this.parse();
    }

    public DocumentNode getDocumentNode() {
        return this.root;
    }

    private void parse() {
        Lexer l = new Lexer(this.body);
    }
}
