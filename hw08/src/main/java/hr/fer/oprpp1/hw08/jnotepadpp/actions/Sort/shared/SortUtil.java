package hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.shared;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.text.Collator;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SortUtil {
    public static void sort(MultipleDocumentModel mdm, ILocalizationProvider lp, boolean ascending) throws BadLocationException {
        JTextArea editor = mdm.getCurrentDocument().getTextComponent();
        Document doc = editor.getDocument();

        int from = editor.getLineStartOffset(editor.getLineOfOffset(editor.getSelectionStart()));
        int to = editor.getLineEndOffset(editor.getLineOfOffset(editor.getSelectionEnd()));

        Collator collator = Collator.getInstance(lp.getLocale());
        String content = Arrays.stream(editor.getText().substring(from, to).split("\n"))
                .sorted(ascending ? collator : collator.reversed())
                .collect(Collectors.joining("\n"));

        doc.remove(from, to - from);
        doc.insertString(from, content, null);
        editor.select(from, to);
    }
}
