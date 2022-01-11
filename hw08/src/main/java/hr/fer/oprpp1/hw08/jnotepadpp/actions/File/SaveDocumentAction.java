package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveDocumentAction extends BaseAction {
    public SaveDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "save");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
