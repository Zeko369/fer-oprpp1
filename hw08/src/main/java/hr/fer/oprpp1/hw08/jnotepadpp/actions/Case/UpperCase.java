package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

public class UpperCase extends BaseAction {
    public UpperCase(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "uppercase");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
