package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.shared.CloseHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends BaseAction {
    private final JFrame app;

    public ExitAction(MultipleDocumentModel mdm, ILocalizationProvider lp, JFrame app) {
        super(mdm, lp, "exit");
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CloseHelper.closeApp(this.mdm, this.app);
    }
}
