package hr.fer.oprpp1.custom.scripting.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
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

