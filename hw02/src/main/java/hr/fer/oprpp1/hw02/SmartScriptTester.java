package hr.fer.oprpp1.hw02;

import hr.fer.oprpp1.custom.scripting.lexer.demo.Loader;
import hr.fer.oprpp1.custom.scripting.node.DocumentNode;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParser;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;

public class SmartScriptTester {
    public static void main(String[] args) {
        String docBody = Loader.loadCode("./demos/code.txt");
        SmartScriptParser parser = null;

        try {
            parser = new SmartScriptParser(docBody);
        } catch (SmartScriptParserException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to parse document!");
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("If this line ever executes, you have failed this class!");
            System.exit(-1);
        }

        DocumentNode document = parser.getDocumentNode();
        String originalDocumentBody = document.toString();
        System.out.println(originalDocumentBody); // should write something like originl content of docBody
    }
}
