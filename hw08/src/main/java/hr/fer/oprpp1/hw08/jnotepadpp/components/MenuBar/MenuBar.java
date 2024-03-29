package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar;

import hr.fer.oprpp1.hw08.jnotepadpp.managers.DisableManager;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus.*;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu bar.
 */
public class MenuBar extends JMenuBar {
    private final List<BaseAction> actions = new ArrayList<>();

    /**
     * Instantiates a new Menu bar.
     *
     * @param mdm the mdm
     * @param flp the flp
     * @param app the app
     * @param dm  the dm
     */
    public MenuBar(MultipleDocumentModel mdm, FormLocalizationProvider flp, JFrame app, DisableManager dm) {
        this.addMenuWithListeners(new FileMenu(mdm, flp, app));
        this.addMenuWithListeners(new EditMenu(mdm, flp));
        this.addMenuWithListeners(new CaseMenu(mdm, flp));
        this.addMenuWithListeners(new SortMenu(mdm, flp));

        this.addMenuWithListeners(new LanguageMenu(flp));

        dm.addAll(this.actions);
    }

    private void addMenuWithListeners(LocalizedJMenu menu) {
        this.actions.addAll(menu.getActions().stream().filter(BaseAction::isDisableable).toList());
        this.add(menu);
    }
}
