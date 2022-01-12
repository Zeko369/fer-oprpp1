package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.shared.SaveHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveDocumentAction extends BaseAction {
    private final JFrame app;

    public SaveDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp, JFrame app) {
        super(mdm, lp, "save");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);

        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveHelper.save(mdm.getCurrentDocument(), mdm, app);
    }
}
