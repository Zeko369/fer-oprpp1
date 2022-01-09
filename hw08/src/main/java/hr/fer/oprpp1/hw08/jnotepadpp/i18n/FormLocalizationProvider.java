package hr.fer.oprpp1.hw08.jnotepadpp.i18n;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormLocalizationProvider extends LocalizationProviderBridge {
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
