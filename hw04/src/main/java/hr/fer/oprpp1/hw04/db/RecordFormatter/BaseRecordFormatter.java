package hr.fer.oprpp1.hw04.db.RecordFormatter;

public abstract class BaseRecordFormatter {
    protected static String genBorder(int[] maxLengths) {
        StringBuilder sb = new StringBuilder();
        for (int maxLength : maxLengths) {
            sb.append("+");
            sb.append("=".repeat(maxLength));
        }
        sb.append("+");

        return sb.toString();
    }

    protected static String formatCell(int maxSize, String value) {
        return "| " + value + " ".repeat(maxSize - value.length() - 1);
    }

    protected static String endFormatLine(int count) {
        return String.format("Records selected: %d", count);
    }
}
