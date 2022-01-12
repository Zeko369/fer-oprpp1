package hr.fer.oprpp1.hw08.jnotepadpp.shared;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.List;

/**
 * The type Get selected lines.
 */
public class GetSelectedLines {
    /**
     * Gets selected lines.
     *
     * @param editor the editor
     * @return the selected lines
     * @throws BadLocationException the bad location exception
     */
    public static List<String> getSelectedLines(JTextArea editor) throws BadLocationException {
        int from = editor.getLineStartOffset(editor.getLineOfOffset(editor.getSelectionStart()));
        int to = editor.getLineEndOffset(editor.getLineOfOffset(editor.getSelectionEnd()));

        return List.of(editor.getText().substring(from, to).split("\n"));
    }

    /**
     * Replace selected lines.
     *
     * @param editor  the editor
     * @param content the content
     * @throws BadLocationException the bad location exception
     */
    public static void replaceSelectedLines(JTextArea editor, String content) throws BadLocationException {
        Document doc = editor.getDocument();

        int from = editor.getLineStartOffset(editor.getLineOfOffset(editor.getSelectionStart()));
        int to = editor.getLineEndOffset(editor.getLineOfOffset(editor.getSelectionEnd()));

        doc.remove(from, to - from);
        doc.insertString(from, content, null);
        editor.select(from, to);
    }
}
