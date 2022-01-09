package hr.fer.oprpp1.hw08.jnotepadpp.i18n.components;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;

public abstract class LocalizableAction extends AbstractAction {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String key;
    private final ILocalizationProvider lp;

    public LocalizableAction(String key, ILocalizationProvider lp) {
        this.key = key;
        this.lp = lp;

        lp.addLocalizationListener(this::setName);
        this.setName();
    }

    private void setName() {
        this.putValue(Action.NAME, this.lp.getString(this.key));
    }
}
