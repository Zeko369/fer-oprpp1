package hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.shared;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.shared.GetSelectedLines;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.text.Collator;
import java.util.stream.Collectors;

public class SortUtil {
    public static void sort(MultipleDocumentModel mdm, ILocalizationProvider lp, boolean ascending) throws BadLocationException {
        JTextArea editor = mdm.getCurrentDocument().getTextComponent();

        Collator collator = Collator.getInstance(lp.getLocale());
        String content = GetSelectedLines.getSelectedLines(editor)
                .stream()
                .sorted(ascending ? collator : collator.reversed())
                .collect(Collectors.joining("\n"));

        GetSelectedLines.replaceSelectedLines(editor, content);
    }
}
