package hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

public class Paste extends BaseAction {
    public Paste(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "paste");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
