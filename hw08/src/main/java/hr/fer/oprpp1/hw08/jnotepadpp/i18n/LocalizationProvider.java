package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationProvider extends AbstractLocalizationProvider {
    private Language language;
    private ResourceBundle bundle;

    private final static LocalizationProvider instance = new LocalizationProvider();

    public LocalizationProvider() {
        this.language = Language.en;
        this.bundle = this.getBundle(this.language);
    }

    private ResourceBundle getBundle(Language language) {
        return ResourceBundle.getBundle("translations", Locale.forLanguageTag(language.toString()));
    }

    public LocalizationProvider getInstance() {
        return LocalizationProvider.instance;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
        this.bundle = this.getBundle(this.language);

        this.fire();
    }

    public String getString(String key) {
        return this.bundle.getString(key);
    }
}
