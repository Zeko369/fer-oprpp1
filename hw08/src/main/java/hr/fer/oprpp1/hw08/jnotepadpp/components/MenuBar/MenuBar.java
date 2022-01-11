package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.File.ExitAction;
import hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus.*;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar(MultipleDocumentModel mdm, FormLocalizationProvider flp, JFrame app) {
        this.add(new FileMenu(mdm, flp, app));
        this.add(new EditMenu(mdm, flp));
        this.add(new CaseMenu(mdm, flp));
        this.add(new SortMenu(mdm, flp));

        this.add(new LanguageMenu(flp));
    }
}
