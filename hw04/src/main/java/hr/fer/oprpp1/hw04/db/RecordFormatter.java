package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;

public class RecordFormatter {
    public static List<String> format(List<StudentRecord> records) {
        int[] maxLengths = new int[4];
        maxLengths[3] = 3;

        records.forEach(record -> {
            maxLengths[0] = Math.max(maxLengths[0], record.getJmbag().length() + 2);
            maxLengths[1] = Math.max(maxLengths[1], record.getFirstName().length() + 2);
            maxLengths[2] = Math.max(maxLengths[2], record.getLastName().length() + 2);
        });

        List<String> formattedRecords = new ArrayList<>();
        formattedRecords.add(genBorder(maxLengths));
        formattedRecords.addAll(records.stream().map(r -> formatRow(maxLengths, r)).toList());
        formattedRecords.add(genBorder(maxLengths));

        return formattedRecords;
    }

    private static String genBorder(int[] maxLengths) {
        StringBuilder sb = new StringBuilder();
        for (int maxLength : maxLengths) {
            sb.append("+");
            sb.append("=".repeat(maxLength));
        }
        sb.append("+");

        return sb.toString();
    }

    private static String formatRow(int [] maxLengths, StudentRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatCell(maxLengths[0], record.getJmbag()));
        sb.append(formatCell(maxLengths[1], record.getFirstName()));
        sb.append(formatCell(maxLengths[2], record.getLastName()));
        sb.append(formatCell(maxLengths[3], String.valueOf(record.getFinalGrade())));
        sb.append("|");

        return sb.toString();
    }

    private static String formatCell(int maxSize, String value) {
        return "| " + value + " ".repeat(maxSize - value.length() - 1);
    }
}
