package hr.fer.oprpp1.hw08.jnotepadpp.i18n.components;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;

public class LocalizedJToolBar extends JToolBar {
    @Serial
    private static final long serialVersionUID = 1L;

    public LocalizedJToolBar(String key, ILocalizationProvider lp) {
        this.setToolTipText(lp.getString(key));
        this.setName(lp.getString(key));

        lp.addLocalizationListener(() -> {
            this.setToolTipText(lp.getString(key));
            this.setName(lp.getString(key));
        });
    }
}
