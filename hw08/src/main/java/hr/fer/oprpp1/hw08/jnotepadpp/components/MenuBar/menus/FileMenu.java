package hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.menus;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.File.*;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class FileMenu extends LocalizedJMenu {
    public FileMenu(MultipleDocumentModel mdm, ILocalizationProvider lp, JFrame app) {
        super("file_menu", lp);

        this.add(new NewDocumentAction(mdm, lp));
        this.add(new OpenDocumentAction(mdm, lp, app));
        this.add(new SaveDocumentAction(mdm, lp, app));
        this.add(new SaveAsDocumentAction(mdm, lp, app));
        this.add(new StatsDocumentAction(mdm, lp, app));
        this.add(new CloseDocumentAction(mdm, lp));

        this.add(new JPopupMenu.Separator());
        this.add(new ExitAction(mdm, lp, app));
    }
}
