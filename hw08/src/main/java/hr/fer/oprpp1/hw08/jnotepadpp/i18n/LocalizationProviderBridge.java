package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import java.util.Locale;

public class LocalizationProviderBridge extends AbstractLocalizationProvider implements ILocalizationProvider {
    private boolean connected;
    private final ILocalizationProvider provider;

    private final ILocalizationListener listener = () -> this.listeners.forEach(ILocalizationListener::localizationChanged);

    public LocalizationProviderBridge(ILocalizationProvider provider) {
        this.provider = provider;
    }


    public void disconnect() {
        if (this.connected) {
            this.connected = false;
            this.provider.removeLocalizationListener(this.listener);
        }
    }

    public void connect() {
        if (!this.connected) {
            this.connected = true;
            this.provider.addLocalizationListener(this.listener);
        }
    }

    public String getString(String key) {
        return this.provider.getString(key);
    }

    @Override
    public Locale getLocale() {
        return this.provider.getLocale();
    }
}
