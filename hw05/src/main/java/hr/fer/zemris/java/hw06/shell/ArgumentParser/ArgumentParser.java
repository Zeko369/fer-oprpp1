package hr.fer.zemris.java.hw06.shell.ArgumentParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Util for parsing arguments
 *
 * @author franzekan
 */
public class ArgumentParser {
    /**
     * Single param parse string.
     *
     * @param arguments the arguments
     * @return the string
     * @throws NullPointerException    if arguments is null
     * @throws ArgumentParserException if there is too many/few arguments or if there was an error parsing the arguments
     */
    public static String singleParamParse(String arguments) throws NullPointerException, ArgumentParserException {
        String[] args = ArgumentParser.parse(arguments, 1);
        return args[0];
    }

    /**
     * Parse any number of arguments (min and max limits).
     *
     * @param arguments the arguments
     * @param min       the min
     * @param max       the max
     * @return the string [ ]
     * @throws IllegalArgumentException if min > max or one of them is 0
     * @throws NullPointerException     if arguments is null
     * @throws ArgumentParserException  if there is too many/few arguments or if there was an error parsing the arguments
     */
    public static String[] parse(String arguments, int min, int max) throws IllegalArgumentException, NullPointerException, ArgumentParserException {
        if (Objects.requireNonNull(arguments).length() == 0 && min == 0) {
            return new String[0];
        }

        if (min < 0 || max < 0) {
            throw new IllegalArgumentException("Min/Max must be positive.");
        }

        if (min > max) {
            throw new IllegalArgumentException("Minimal number of arguments cannot be greater than maximal number of arguments.");
        }

        List<String> args = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean inQuotes = false;
        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.charAt(i) == '"') {
                if (sb.length() > 0) {
                    args.add(sb.toString());
                    sb = new StringBuilder();
                }
                inQuotes = !inQuotes;

                if (!inQuotes && i + 1 < arguments.length() && arguments.charAt(i + 1) != ' ') {
                    throw new ArgumentParserException("Argument must be separated by space.");
                }
            } else if (arguments.charAt(i) == ' ' && !inQuotes && sb.length() > 0) {
                args.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(arguments.charAt(i));
            }
        }

        if (sb.length() > 0) {
            args.add(sb.toString());
        }

        if (args.size() < min) {
            throw new ArgumentParserException("Not enough arguments.");
        }

        if (args.size() > max) {
            throw new ArgumentParserException("Too many arguments.");
        }

        return args.toArray(new String[0]);
    }

    /**
     * Parse specific number of arguments (exact count).
     *
     * @param arguments the arguments
     * @param count     the count
     * @return the string [ ]
     * @throws IllegalArgumentException if count < 0
     * @throws NullPointerException     if arguments is null
     * @throws ArgumentParserException  if there is too many/few arguments or if there was an error parsing the arguments
     */
    public static String[] parse(String arguments, int count) throws IllegalArgumentException, NullPointerException, ArgumentParserException {
        return ArgumentParser.parse(arguments, count, count);
    }
}
