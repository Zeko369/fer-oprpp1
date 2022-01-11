package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.ChangeLanguageAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.Language;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;

public class LanguageMenu extends LocalizedJMenu {
    public LanguageMenu(ILocalizationProvider lp) {
        super("language_menu", lp);

        for (Language lang : lp.getSupportedLanguages()) {
            this.add(new ChangeLanguageAction(lp, lang));
        }
    }
}
