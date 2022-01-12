package hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * The type Paste action.
 */
public class PasteAction extends BaseAction {
    /**
     * Instantiates a new Paste action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public PasteAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "paste");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea editor = mdm.getCurrentDocument().getTextComponent();

        try {
            editor.insert(
                    (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor),
                    editor.getSelectionStart()
            );
        } catch (UnsupportedFlavorException | IOException ignored) {
        }
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
