package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared.CaseEnum;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared.CaseHelper;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import java.awt.event.ActionEvent;

/**
 * The type Lower case.
 */
public class LowerCase extends BaseAction {
    /**
     * Instantiates a new Lower case.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public LowerCase(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "lowercase");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CaseHelper.changeCase(this.mdm.getCurrentDocument(), CaseEnum.LOWER);
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
