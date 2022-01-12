package hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * The type Copy action.
 */
public class CopyAction extends BaseAction {
    /**
     * Instantiates a new Copy action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public CopyAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(mdm.getCurrentDocument().getTextComponent().getSelectedText()), null);
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
