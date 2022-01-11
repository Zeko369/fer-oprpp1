package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.InvertCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.LowerCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.UpperCase;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

public class CaseMenu extends LocalizedJMenu {
    public CaseMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("case_menu", lp);

        this.add(new InvertCase(mdm, lp));
        this.add(new UpperCase(mdm, lp));
        this.add(new LowerCase(mdm, lp));
    }
}
