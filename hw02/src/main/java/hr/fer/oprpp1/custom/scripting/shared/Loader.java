package hr.fer.oprpp1.custom.scripting.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Helper for reading the file
 *
 * @author franzekan
 */
public class Loader {
    /**
     * Load code string.
     *
     * @param filename the filename
     * @return the string
     */
    public static String loadCode(String filename) {
        StringBuilder sb = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String tmp = scanner.nextLine();
                if (tmp.startsWith("#")) {
                    continue;
                }

                sb.append(tmp);
                sb.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return sb.toString();
    }
}

