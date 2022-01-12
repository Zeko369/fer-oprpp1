package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.Locale;

public interface ILocalizationProvider {
    String getString(String key);

    void addLocalizationListener(ILocalizationListener listener);
    void removeLocalizationListener(ILocalizationListener listener);

    Language[] getSupportedLanguages();
    Locale getLocale();

    void fire();
}
