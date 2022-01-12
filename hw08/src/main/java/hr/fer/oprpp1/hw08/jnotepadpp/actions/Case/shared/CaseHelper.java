package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared;

import hr.fer.oprpp1.hw08.jnotepadpp.model.SingleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * The type Case helper.
 */
public class CaseHelper {
    /**
     * Change case.
     *
     * @param model    the model
     * @param textCase the text case
     */
    public static void changeCase(SingleDocumentModel model, CaseEnum textCase) {
        String selectedText = model.getTextComponent().getSelectedText();
        if (selectedText == null) {
            return;
        }

        JTextArea textArea = model.getTextComponent();
        Document doc = model.getTextComponent().getDocument();
        try {
            int start = textArea.getSelectionStart();

            doc.remove(start, textArea.getSelectionEnd() - textArea.getSelectionStart());
            doc.insertString(start, CaseHelper.convert(selectedText, textCase), null);
            textArea.select(start, start + selectedText.length());
        } catch (BadLocationException ignored) {
        }
    }

    private static String convert(String text, CaseEnum textCase) {
        return switch (textCase) {
            case UPPER -> text.toUpperCase();
            case LOWER -> text.toLowerCase();
            case INVERT -> text.chars()
                    .map(c -> Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c))
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        };
    }
}
