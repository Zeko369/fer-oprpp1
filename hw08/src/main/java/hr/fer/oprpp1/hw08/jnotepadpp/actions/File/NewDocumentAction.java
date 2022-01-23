package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The type New document action.
 */
public class NewDocumentAction extends BaseAction {
    /**
     * Instantiates a new New document action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public NewDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "new");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control N"));
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mdm.createNewDocument();
    }
}
