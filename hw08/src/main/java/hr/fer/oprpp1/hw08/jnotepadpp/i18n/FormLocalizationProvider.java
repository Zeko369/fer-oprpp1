package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Form localization provider.
 */
public class FormLocalizationProvider extends LocalizationProviderBridge {
    /**
     * Instantiates a new Form localization provider.
     *
     * @param provider the provider
     * @param app      the app
     */
    public FormLocalizationProvider(ILocalizationProvider provider, JFrame app) {
        super(provider);

        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                FormLocalizationProvider.this.connect();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                FormLocalizationProvider.this.disconnect();
            }
        });
    }
}
