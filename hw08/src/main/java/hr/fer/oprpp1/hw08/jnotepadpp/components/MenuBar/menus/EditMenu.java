package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Copy;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Cut;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.DeleteSelected;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Paste;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class EditMenu extends LocalizedJMenu {
    public EditMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("edit_menu", lp);

        this.add(new Copy(mdm, lp));
        this.add(new Cut(mdm, lp));
        this.add(new Paste(mdm, lp));

        this.add(new JPopupMenu.Separator());

        this.add(new DeleteSelected(mdm, lp));
    }
}
