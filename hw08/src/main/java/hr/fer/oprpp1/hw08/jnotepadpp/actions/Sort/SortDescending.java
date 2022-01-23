package hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.shared.SortUtil;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The type Sort descending.
 */
public class SortDescending extends BaseAction {
    /**
     * Instantiates a new Sort descending.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public SortDescending(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "descending");

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F6"));
        this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F6);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            SortUtil.sort(this.mdm, this.lp, false);
        } catch (BadLocationException ignored) {
        }
    }


    @Override
    public boolean isDisableable() {
        return true;
    }
}
