package hr.fer.oprpp1.hw08.jnotepadpp.actions.Case;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared.CaseEnum;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.shared.CaseHelper;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control L"));
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
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
