package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.ClipboardCopyAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.ClipboardCutAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.DeleteSelected;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.ClipboardPasteAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class EditMenu extends LocalizedJMenu {
    public EditMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("edit_menu", lp);

        this.add(new ClipboardCopyAction(mdm, lp));
        this.add(new ClipboardCutAction(mdm, lp));
        this.add(new ClipboardPasteAction(mdm, lp));

        this.add(new JPopupMenu.Separator());

        this.add(new DeleteSelected(mdm, lp));
    }
}
