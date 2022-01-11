package hr.fer.oprpp1.hw08.jnotepadpp.i18n.components;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;

import javax.swing.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class LocalizedJMenu extends JMenu {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<BaseAction> actions = new ArrayList<>();

    public LocalizedJMenu(String key, ILocalizationProvider lp) {
        this.setText(lp.getString(key));

        lp.addLocalizationListener(() -> this.setText(lp.getString(key)));
    }

    public List<BaseAction> getActions() {
        return this.actions;
    }

    public JMenuItem add(BaseAction action) {
        this.actions.add(action);
        return super.add(action);
    }
}
