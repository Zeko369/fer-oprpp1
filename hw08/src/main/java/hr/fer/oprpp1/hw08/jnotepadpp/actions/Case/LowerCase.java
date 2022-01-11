package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

public class LowerCase extends BaseAction {
    public LowerCase(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "lowercase");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
