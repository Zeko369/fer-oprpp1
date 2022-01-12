package hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.shared.SortUtil;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;

public class SortDescending extends BaseAction {
    public SortDescending(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super(mdm, lp, "descending");
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
