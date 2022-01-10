package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

public interface ILocalizationProvider {
    String getString(String key);

    void addLocalizationListener(ILocalizationListener listener);
    void removeLocalizationListener(ILocalizationListener listener);

    Language[] getSupportedLanguages();

    void fire();
}
