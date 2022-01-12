package hr.fer.oprpp1.hw08.jnotepadpp.i18n.components;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Localized j menu.
 */
public class LocalizedJMenu extends JMenu {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<BaseAction> actions = new ArrayList<>();

    /**
     * Instantiates a new Localized j menu.
     *
     * @param key the key
     * @param lp  the lp
     */
    public LocalizedJMenu(String key, ILocalizationProvider lp) {
        this.setText(lp.getString(key));

        lp.addLocalizationListener(() -> this.setText(lp.getString(key)));
    }

    /**
     * Gets actions.
     *
     * @return the actions
     */
    public List<BaseAction> getActions() {
        return this.actions;
    }

    /**
     * Add j menu item.
     *
     * @param action the action
     * @return the j menu item
     */
    public JMenuItem add(BaseAction action) {
        this.actions.add(action);
        return super.add(action);
    }
}
