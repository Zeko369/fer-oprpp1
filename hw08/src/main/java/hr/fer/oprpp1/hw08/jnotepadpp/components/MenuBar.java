package hr.fer.oprpp1.hw08.jnotepadpp.components;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.FileMenu.FileMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.LanguageMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar(MultipleDocumentModel mdm, FormLocalizationProvider flp) {
        this.add(new FileMenu(mdm, flp));
        this.add(new LanguageMenu(flp));
    }
}
