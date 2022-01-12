package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Localization provider.
 */
public class LocalizationProvider extends AbstractLocalizationProvider {
    private Language language;
    private ResourceBundle bundle;

    private final static LocalizationProvider instance = new LocalizationProvider();

    private LocalizationProvider() {
        this.language = Language.en;
        this.bundle = this.getBundle();
    }

    private ResourceBundle getBundle() {
        Locale l = new Locale(this.language.toString());
        return ResourceBundle.getBundle("i18n.translations", l);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static LocalizationProvider getInstance() {
        return LocalizationProvider.instance;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public Language getLanguage() {
        return this.language;
    }

    public Locale getLocale() {
        return new Locale(this.language.toString());
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(Language language) {
        this.language = language;
        this.bundle = this.getBundle();

        this.fire();
    }

    public String getString(String key) {
        return this.bundle.getString(key);
    }
}
