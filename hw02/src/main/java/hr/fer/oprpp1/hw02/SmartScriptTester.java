package hr.fer.oprpp1.hw02;

import hr.fer.oprpp1.custom.scripting.node.DocumentNode;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParser;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import hr.fer.oprpp1.custom.shared.Loader;

public class SmartScriptTester {
    public static void main(String[] args) {
//        SmartScriptTester.runFilename("./demos/primjer1.txt");

        for(int i = 1; i < 10; i++) {
            System.out.printf("--------- %d ---------\n", i);
            SmartScriptTester.runFilename(String.format("./demos/primjer%d.txt", i));
        }
    }

    private static void runFilename(String filename) {
        String docBody = Loader.loadCode(filename);
        System.out.println(docBody);

        DocumentNode parsedTree = SmartScriptTester.parse(docBody);
        if(parsedTree == null) {
            return;
        }

        String originalDocumentBody = parsedTree.toString();
//        String reParsedDocumentBody = SmartScriptTester.parse(originalDocumentBody).toString();

        System.out.println(originalDocumentBody);
//        System.out.println("---------------------");
//        System.out.println(reParsedDocumentBody);
//
//        System.out.println("---------------------");
//        if(originalDocumentBody.equals(reParsedDocumentBody)) {
//            System.out.println("Same");
//        } else {
//            System.out.println("Didn't work :(");
//        }
//        System.out.println("\n---------------------");

        System.out.println(parsedTree.toStructure());
    }

    private static DocumentNode parse(String code) {
        SmartScriptParser parser = null;

        try {
            parser = new SmartScriptParser(code);
        } catch (SmartScriptParserException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to parse document!");
            return null;
        } catch (Exception e) {
            System.out.println("If this line ever executes, you have failed this class!");
            System.exit(-1);
        }

        return parser.getDocumentNode();
    }
}
