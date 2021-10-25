package hr.fer.oprpp1.hw02;

import hr.fer.oprpp1.custom.scripting.node.DocumentNode;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParser;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import hr.fer.oprpp1.custom.scripting.shared.Loader;

public class SmartScriptTester {
    public static void main(String[] args) {
        String docBody = Loader.loadCode("./demos/code.txt");
        System.out.println(docBody);

        DocumentNode parsedTree = SmartScriptTester.parse(docBody);
        String originalDocumentBody = parsedTree.toString();
        String reParsedDocumentBody = SmartScriptTester.parse(originalDocumentBody).toString();

        System.out.println(originalDocumentBody);
//        System.out.println("---------------------");
//        System.out.println(reParsedDocumentBody);

        System.out.println(parsedTree.toStructure());

        if(originalDocumentBody.equals(reParsedDocumentBody)) {
            System.out.println("Same");
        } else {
            System.out.println("Didn't work :(");
        }
    }

    private static DocumentNode parse(String code) {
        SmartScriptParser parser = null;

        try {
            parser = new SmartScriptParser(code);
        } catch (SmartScriptParserException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to parse document!");
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("If this line ever executes, you have failed this class!");
            System.exit(-1);
        }

        return parser.getDocumentNode();
    }
}
