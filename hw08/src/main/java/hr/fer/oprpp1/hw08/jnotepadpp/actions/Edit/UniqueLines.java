package hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.shared.GetSelectedLines;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueLines extends BaseAction {
    public UniqueLines(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "unique");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea editor = mdm.getCurrentDocument().getTextComponent();

        try {
            List<String> lines = new ArrayList<>();
            GetSelectedLines.getSelectedLines(editor).forEach(line -> {
                if(!lines.contains(line)) {
                    lines.add(line);
                }
            });

            GetSelectedLines.replaceSelectedLines(editor, String.join("\n", lines));
        } catch (BadLocationException ignored) {
        }
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
