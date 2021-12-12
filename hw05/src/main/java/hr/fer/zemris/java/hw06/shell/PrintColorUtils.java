package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;

public class PrintColorUtils {
    public enum Color {
        RED,
        CYAN,
        PURPLE
    }

    private static final SortedMap<Color, String> COLORS = new java.util.TreeMap<>();
    private static final String RESET = "\u001B[0m";

    static {
        COLORS.put(Color.RED, "\u001B[31m");
        COLORS.put(Color.CYAN, "\u001B[36m");
        COLORS.put(Color.PURPLE, "\u001B[35m");
    }

    public static String colorPrint(String text, Color color) {
        return COLORS.get(color) + text + RESET;
    }
}
