package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract localization provider.
 */
public abstract class AbstractLocalizationProvider implements ILocalizationProvider {
    /**
     * The Listeners.
     */
    protected final List<ILocalizationListener> listeners = new ArrayList<>();

    public void addLocalizationListener(ILocalizationListener l) {
        this.listeners.add(l);
    }

    public void removeLocalizationListener(ILocalizationListener l) {
        this.listeners.remove(l);
    }

    public void fire() {
        this.listeners.forEach(ILocalizationListener::localizationChanged);
    }

    @Override
    public Language[] getSupportedLanguages() {
        return new Language[]{
                Language.en,
                Language.hr,
                Language.de
        };
    }
}
