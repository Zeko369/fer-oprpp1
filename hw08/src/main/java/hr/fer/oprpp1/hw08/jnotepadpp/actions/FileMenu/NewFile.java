package hr.fer.oprpp1.hw08.jnotepadpp.actions.FileMenu;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

public class NewFile extends BaseAction {
    public NewFile(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "new");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mdm.createNewDocument();
    }
}
