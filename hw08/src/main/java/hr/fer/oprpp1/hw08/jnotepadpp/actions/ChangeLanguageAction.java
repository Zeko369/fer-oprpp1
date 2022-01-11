package hr.fer.oprpp1.hw08.jnotepadpp.actions;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.Language;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.LocalizationProvider;

import java.awt.event.ActionEvent;

public class ChangeLanguageAction extends BaseAction{
    private final Language language;

    public ChangeLanguageAction(ILocalizationProvider lp, Language language) {
        super(lp, String.format("language_option_%s", language.toString()));
        this.language = language;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LocalizationProvider.getInstance().setLanguage(this.language);
    }
}
