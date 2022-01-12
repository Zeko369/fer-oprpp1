package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.InvertCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.LowerCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.UpperCase;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

/**
 * The type Case menu.
 */
public class CaseMenu extends LocalizedJMenu {
    /**
     * Instantiates a new Case menu.
     *
     * @param mdm the mdm
     * @param lp  the lp
     */
    public CaseMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("case_menu", lp);

        this.add(new InvertCase(mdm, lp));
        this.add(new UpperCase(mdm, lp));
        this.add(new LowerCase(mdm, lp));
    }
}
