package hr.fer.oprpp1.hw04.db;

/**
 * Container for predefined getters for field values.
 *
 * @author franzekan
 */
public class FieldValueGetters {
    /**
     * The constant JMBAG.
     */
    public static final IFieldValueGetter JMBAG = StudentRecord::getJmbag;
    /**
     * The constant FIRST_NAME.
     */
    public static final IFieldValueGetter FIRST_NAME = StudentRecord::getFirstName;
    /**
     * The constant LAST_NAME.
     */
    public static final IFieldValueGetter LAST_NAME = StudentRecord::getLastName;

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     * @throws IllegalArgumentException if name is not recognized
     */
    public static IFieldValueGetter getByName(String name) throws IllegalArgumentException {
        return switch (name.toUpperCase()) {
            case "JMBAG" -> JMBAG;
            // TODO: Implement smart camelcase -> snakeCase converter
            case "FIRST_NAME", "FIRSTNAME" -> FIRST_NAME;
            case "LAST_NAME", "LASTNAME" -> LAST_NAME;
            default -> throw new IllegalArgumentException("Unknown field name: " + name);
        };
    }
}
