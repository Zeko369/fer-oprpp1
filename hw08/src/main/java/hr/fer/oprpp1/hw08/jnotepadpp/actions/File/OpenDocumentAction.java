package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

public class OpenDocumentAction extends BaseAction {
    public OpenDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
