package hr.fer.oprpp1.hw08.jnotepadpp.i18n.components;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;

public class LocalizedJMenu extends JMenu {
    @Serial
    private static final long serialVersionUID = 1L;

    public LocalizedJMenu(String key, ILocalizationProvider lp) {
        this.setText(lp.getString(key));

        lp.addLocalizationListener(() -> {
            this.setText(lp.getString(key));
        });
    }
}
