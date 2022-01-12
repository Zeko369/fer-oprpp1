package hr.fer.oprpp1.hw08.jnotepadpp.i18n.utils;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;

/**
 * The type Localizable action.
 */
public abstract class LocalizableAction extends AbstractAction {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String key;
    private final ILocalizationProvider lp;

    /**
     * Instantiates a new Localizable action.
     *
     * @param key the key
     * @param lp  the lp
     */
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
