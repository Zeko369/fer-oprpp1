package hr.fer.oprpp1.hw04.db;

public class FieldValueGetters {
    public static final IFieldValueGetter JMBAG = StudentRecord::getJmbag;
    public static final IFieldValueGetter FIRST_NAME = StudentRecord::getFirstName;
    public static final IFieldValueGetter LAST_NAME = StudentRecord::getLastName;

    public static IFieldValueGetter getByName(String name) {
        return switch (name.toUpperCase()) {
            case "JMBAG" -> JMBAG;
            // TODO: Implement smart camelcase -> snakecase converter
            case "FIRST_NAME", "FIRSTNAME" -> FIRST_NAME;
            case "LAST_NAME", "LASTNAME" -> LAST_NAME;
            default -> throw new IllegalArgumentException("Unknown field name: " + name);
        };
    }
}
