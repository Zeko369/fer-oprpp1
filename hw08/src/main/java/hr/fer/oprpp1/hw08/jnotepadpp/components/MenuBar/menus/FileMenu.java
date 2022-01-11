package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.File.*;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class FileMenu extends LocalizedJMenu {
    public FileMenu(MultipleDocumentModel mdm, ILocalizationProvider lp, JFrame app) {
        super("file_menu", lp);

        this.add(new NewFile(mdm, lp));
        this.add(new SaveFile(mdm, lp));
        this.add(new SaveAsFile(mdm, lp));
        this.add(new CloseFile(mdm, lp));

        this.add(new JPopupMenu.Separator());
        this.add(new ExitAction(mdm, lp, app));
    }
}
