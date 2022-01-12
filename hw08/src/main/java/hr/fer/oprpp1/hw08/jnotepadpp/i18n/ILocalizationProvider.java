package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.Locale;

/**
 * The interface Localization provider.
 */
public interface ILocalizationProvider {
    /**
     * Gets string.
     *
     * @param key the key
     * @return the string
     */
    String getString(String key);

    /**
     * Add localization listener.
     *
     * @param listener the listener
     */
    void addLocalizationListener(ILocalizationListener listener);

    /**
     * Remove localization listener.
     *
     * @param listener the listener
     */
    void removeLocalizationListener(ILocalizationListener listener);

    /**
     * Get supported languages language [ ].
     *
     * @return the language [ ]
     */
    Language[] getSupportedLanguages();

    /**
     * Gets locale.
     *
     * @return the locale
     */
    Locale getLocale();

    /**
     * Fire.
     */
    void fire();
}
