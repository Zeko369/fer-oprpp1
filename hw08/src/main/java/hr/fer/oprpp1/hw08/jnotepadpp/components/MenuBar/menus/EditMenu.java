package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard.CopyAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard.CutAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.DeleteSelected;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard.PasteAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class EditMenu extends LocalizedJMenu {
    public EditMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("edit_menu", lp);

        this.add(new CopyAction(mdm, lp));
        this.add(new CutAction(mdm, lp));
        this.add(new PasteAction(mdm, lp));

        this.add(new JPopupMenu.Separator());

        this.add(new DeleteSelected(mdm, lp));
    }
}
