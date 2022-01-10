package hr.fer.oprpp1.hw08.jnotepadpp.actions;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.Language;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.LocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.utils.LocalizableAction;

import java.awt.event.ActionEvent;

public class LanguageMenu extends LocalizedJMenu {
    public LanguageMenu(ILocalizationProvider lp) {
        super("language_menu", lp);

        for (Language lang : lp.getSupportedLanguages()) {
            this.add(new LocalizableAction(String.format("language_option_%s", lang.toString()), lp) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalizationProvider.getInstance().setLanguage(lang);
                }
            });
        }
    }
}
