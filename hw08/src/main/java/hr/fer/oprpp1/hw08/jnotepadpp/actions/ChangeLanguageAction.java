package hr.fer.oprpp1.hw08.jnotepadpp.actions;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.Language;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.LocalizationProvider;

import java.awt.event.ActionEvent;

/**
 * The type Change language action.
 */
public class ChangeLanguageAction extends BaseAction{
    private final Language language;

    /**
     * Instantiates a new Change language action.
     *
     * @param lp       the lp
     * @param language the language
     */
    public ChangeLanguageAction(ILocalizationProvider lp, Language language) {
        super(lp, String.format("language_option_%s", language.toString()));
        this.language = language;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LocalizationProvider.getInstance().setLanguage(this.language);
    }
}
