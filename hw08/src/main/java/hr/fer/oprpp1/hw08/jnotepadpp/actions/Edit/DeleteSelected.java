package hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;

/**
 * The type Delete selected.
 */
public class DeleteSelected extends BaseAction {
    /**
     * Instantiates a new Delete selected.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public DeleteSelected(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "delete_selected");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JTextArea textArea = mdm.getCurrentDocument().getTextComponent();
            textArea.getDocument().remove(textArea.getSelectionStart(), textArea.getSelectionEnd() - textArea.getSelectionStart());
        } catch (BadLocationException ignored) {
        }
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
