package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

/**
 * The type Close document action.
 */
public class CloseDocumentAction extends BaseAction {
    /**
     * Instantiates a new Close document action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public CloseDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "close");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mdm.closeDocument(this.mdm.getCurrentDocument());
    }
}
