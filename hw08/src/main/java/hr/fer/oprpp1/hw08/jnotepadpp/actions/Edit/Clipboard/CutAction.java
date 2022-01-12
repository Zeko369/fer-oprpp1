package hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * The type Cut action.
 */
public class CutAction extends BaseAction {
    /**
     * Instantiates a new Cut action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public CutAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "cut");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = mdm.getCurrentDocument().getTextComponent();
        String text = textArea.getSelectedText();

        try {
            textArea.getDocument().remove(textArea.getSelectionStart(), textArea.getSelectionEnd() - textArea.getSelectionStart());
        } catch (BadLocationException ignored) {
        } finally {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(text), null);
        }
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
