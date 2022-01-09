package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

public class LocalizationProviderBridge extends AbstractLocalizationProvider {
    private boolean connected;
    private final ILocalizationProvider provider;

    private final ILocalizationListener listener = () -> this.listeners.forEach(ILocalizationListener::localizationChanged);

    public LocalizationProviderBridge(ILocalizationProvider provider) {
        this.provider = provider;
    }

    public void disconnect() {
        if (this.connected) {
            this.connected = false;
            this.listeners.remove(this.listener);
        }
    }

    public void connect() {
        if (!this.connected) {
            this.connected = true;
            this.listeners.add(this.listener);
        }
    }

    public String getString(String key) {
        return this.provider.getString(key);
    }
}
