package hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.shared.SortUtil;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;

/**
 * The type Sort ascending.
 */
public class SortAscending extends BaseAction {
    /**
     * Instantiates a new Sort ascending.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public SortAscending(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "ascending");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            SortUtil.sort(this.mdm, this.lp, true);
        } catch (BadLocationException ignored) {
        }
    }

    @Override
    public boolean isDisableable() {
        return true;
    }
}
