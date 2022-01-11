package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

public class InvertCase extends BaseAction {
    public InvertCase(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "invert");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
