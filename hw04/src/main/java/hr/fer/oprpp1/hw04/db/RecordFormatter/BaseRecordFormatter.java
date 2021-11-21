package hr.fer.oprpp1.hw04.db.RecordFormatter;

/**
 * Helper used in all record formatters
 *
 * @author franzekan
 */
public abstract class BaseRecordFormatter {
    /**
     * Gen border string.
     *
     * @param maxLengths the max lengths
     * @return the string
     */
    protected static String genBorder(int[] maxLengths) {
        StringBuilder sb = new StringBuilder();
        for (int maxLength : maxLengths) {
            sb.append("+");
            sb.append("=".repeat(maxLength));
        }
        sb.append("+");

        return sb.toString();
    }

    /**
     * Format cell string.
     *
     * @param maxSize the max size of value in that column
     * @param value   the value
     * @return column string
     */
    protected static String formatCell(int maxSize, String value) {
        return "| " + value + " ".repeat(maxSize - value.length() - 1);
    }

    /**
     * End format line string.
     *
     * @param count the count
     * @return the string
     */
    protected static String endFormatLine(int count) {
        return String.format("Records selected: %d", count);
    }
}
