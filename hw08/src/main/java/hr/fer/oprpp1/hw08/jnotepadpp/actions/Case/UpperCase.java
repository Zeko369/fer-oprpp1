package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared.CaseEnum;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared.CaseHelper;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

/**
 * The type Upper case.
 */
public class UpperCase extends BaseAction {
    /**
     * Instantiates a new Upper case.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public UpperCase(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "uppercase");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CaseHelper.changeCase(this.mdm.getCurrentDocument(), CaseEnum.UPPER);
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
