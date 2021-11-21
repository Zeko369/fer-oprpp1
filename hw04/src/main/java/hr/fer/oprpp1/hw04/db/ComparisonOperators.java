package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * Container for all predefined comparison operators.
 *
 * @author franzekan
 */
public class ComparisonOperators {
    /**
     * The constant LESS.
     */
    public static final IComparisonOperator LESS = (s1, s2) -> compare(s1, s2) < 0;
    /**
     * The constant LESS_OR_EQUALS.
     */
    public static final IComparisonOperator LESS_OR_EQUALS = (s1, s2) -> compare(s1, s2) <= 0;
    /**
     * The constant GREATER.
     */
    public static final IComparisonOperator GREATER = (s1, s2) -> compare(s1, s2) > 0;
    /**
     * The constant GREATER_OR_EQUALS.
     */
    public static final IComparisonOperator GREATER_OR_EQUALS = (s1, s2) -> compare(s1, s2) >= 0;
    /**
     * The constant EQUALS.
     */
    public static final IComparisonOperator EQUALS = (s1, s2) -> compare(s1, s2) == 0;
    /**
     * The constant NOT_EQUALS.
     */
    public static final IComparisonOperator NOT_EQUALS = (s1, s2) -> compare(s1, s2) != 0;

    /**
     * The constant NEVER.
     */
    public static final IComparisonOperator NEVER = (s1, s2) -> false;

    /**
     * The constant LIKE.
     */
    public static final IComparisonOperator LIKE = (s1, s2) -> {
        if (s1.contains("*")) {
            throw new ComparisonOperatorException("Value can't have a wildcard");
        }

        if (!s2.contains("*")) {
            return s1.equals(s2);
        }

        if (s2.indexOf('*') != s2.lastIndexOf('*')) {
            throw new ComparisonOperatorException("Test can't have more than one *");
        }

        if (s2.startsWith("*")) {
            return s1.endsWith(s2.substring(1));
        }
        if (s2.endsWith("*")) {
            return s1.startsWith(s2.substring(0, s2.length() - 1));
        }

        String[] split = s2.split("\\*");
        String start = split[0];
        String end = split[1];

        return (s1.startsWith(start) && s1.endsWith(end)) && s1.length() >= start.length() + end.length();
    };

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     * @throws IllegalArgumentException if name is not valid
     */
    public static IComparisonOperator getByName(String name) throws IllegalArgumentException {
        return switch (name.toUpperCase()) {
            case "<" -> LESS;
            case "<=" -> LESS_OR_EQUALS;
            case ">" -> GREATER;
            case ">=" -> GREATER_OR_EQUALS;
            case "=" -> EQUALS;
            case "!=" -> NOT_EQUALS;
            case "LIKE" -> LIKE;
            default -> throw new IllegalArgumentException("Unknown operator: " + name);
        };
    }

    private static int compare(String s1, String s2) throws NullPointerException {
        return Objects.requireNonNull(s1).compareTo(Objects.requireNonNull(s2));
    }
}
