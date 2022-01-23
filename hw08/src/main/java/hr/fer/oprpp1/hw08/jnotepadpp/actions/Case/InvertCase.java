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
 * The type Invert case.
 */
public class InvertCase extends BaseAction {
    /**
     * Instantiates a new Invert case.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public InvertCase(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "invert");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F2"));
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CaseHelper.changeCase(this.mdm.getCurrentDocument(), CaseEnum.INVERT);
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
