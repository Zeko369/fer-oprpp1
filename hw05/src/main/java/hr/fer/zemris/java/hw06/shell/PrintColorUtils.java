package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;

/**
 * Util for formatting color escape sequences.
 *
 * @author franzekan
 */
public class PrintColorUtils {
    /**
     * The enum Color.
     *
     * @author franzekan
     */
    public enum Color {
        /**
         * Red color.
         */
        RED,
        /**
         * Cyan color.
         */
        CYAN,
        /**
         * Purple color.
         */
        PURPLE
    }

    private static final SortedMap<Color, String> COLORS = new java.util.TreeMap<>();
    private static final String RESET = "\u001B[0m";

    static {
        COLORS.put(Color.RED, "\u001B[31m");
        COLORS.put(Color.CYAN, "\u001B[36m");
        COLORS.put(Color.PURPLE, "\u001B[35m");
    }

    /**
     * Color print string.
     *
     * @param text  the text
     * @param color the color
     * @return the string
     */
    public static String colorPrint(String text, Color color) {
        return COLORS.get(color) + text + RESET;
    }
}
