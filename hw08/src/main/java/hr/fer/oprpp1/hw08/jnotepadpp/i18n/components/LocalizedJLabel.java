package hr.fer.oprpp1.hw08.jnotepadpp.i18n.components;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;

public class LocalizedJLabel extends JLabel {
    @Serial
    private static final long serialVersionUID = 1L;

    public LocalizedJLabel(String key, ILocalizationProvider lp) {
        this.setText(lp.getString(key));
        lp.addLocalizationListener(() -> this.setText(lp.getString(key)));
    }
}
