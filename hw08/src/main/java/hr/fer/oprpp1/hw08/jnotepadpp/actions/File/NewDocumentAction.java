package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mdm.createNewDocument();
    }
}
