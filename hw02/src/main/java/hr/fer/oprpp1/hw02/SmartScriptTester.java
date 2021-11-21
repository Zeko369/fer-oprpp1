package hr.fer.oprpp1.hw02;

import hr.fer.oprpp1.custom.scripting.node.DocumentNode;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParser;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import hr.fer.oprpp1.custom.shared.FileLoader;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * The type Smart script tester.
 *
 * @author franzekan
 */
public class SmartScriptTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                SmartScriptTester.runFilename("./demos/primjer1.txt");
            } else {
                SmartScriptTester.runFilename(args[0]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    private static void runFilename(String filename) throws FileNotFoundException {
        String docBody = FileLoader.loadCode(filename);
        if (docBody == null) {
            throw new FileNotFoundException();
        }

        DocumentNode parsedTree = SmartScriptTester.parse(docBody);
        if (parsedTree == null) {
            return;
        }

        String originalDocumentBody = parsedTree.toString();
        String reParsedDocumentBody = Objects.requireNonNull(SmartScriptTester.parse(originalDocumentBody)).toString();

        System.out.println(originalDocumentBody);

        if (originalDocumentBody.equals(reParsedDocumentBody)) {
            System.out.println("SAME");
        } else {
            System.err.println("Error reParsing");
        }

        System.out.println("----------");
        System.out.println("Structure: ");
        System.out.println(parsedTree.toStructure());
    }

    private static DocumentNode parse(String code) {
        try {
            return (new SmartScriptParser(code)).getDocumentNode();
        } catch (SmartScriptParserException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to parse document!");
            return null;
        }
    }
}
