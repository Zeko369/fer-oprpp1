package hr.fer.oprpp1.hw05.crypto;

import java.util.Scanner;

public class PromptInput {
    public static String getUserInput(String prompt) {
        System.out.print(prompt);
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }
}
