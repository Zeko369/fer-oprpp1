package hr.fer.oprpp1.hw05.crypto;

import java.util.Scanner;

/**
 * Util for getting input from user.
 *
 * @author franzekan
 */
public class PromptInput {
    /**
     * Gets user input.
     *
     * @param prompt the prompt
     * @return the user input
     */
    public static String getUserInput(String prompt) {
        System.out.print(prompt);
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }
}
