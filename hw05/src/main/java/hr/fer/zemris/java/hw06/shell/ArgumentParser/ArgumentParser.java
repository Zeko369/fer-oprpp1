package hr.fer.zemris.java.hw06.shell.ArgumentParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArgumentParser {
    public static String[] parse(String arguments, int min, int max) {
        if (Objects.requireNonNull(arguments).length() == 0) {
            return new String[0];
        }

        if (min <= 0 || max <= 0) {
            throw new IllegalArgumentException("Min/Max must be greater than 0.");
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
            } else if (arguments.charAt(i) == ' ' && !inQuotes) {
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

    public static String[] parse(String arguments, int count) {
        return ArgumentParser.parse(arguments, count, count);
    }
}
