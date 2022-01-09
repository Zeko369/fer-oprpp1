package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLocalizationProvider implements ILocalizationProvider {
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
}
