package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.SortAscending;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Sort.SortDescending;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

public class SortMenu extends LocalizedJMenu {
    public SortMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("sort_menu", lp);

        this.add(new SortAscending(mdm, lp));
        this.add(new SortDescending(mdm, lp));
    }
}
