package hr.fer.oprpp1.hw08.jnotepadpp.actions.FileMenu;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class FileMenu extends LocalizedJMenu {
    public FileMenu(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("file_menu", lp);

        this.add(new NewFile(mdm, lp));
        this.add(new SaveFile(mdm, lp));
        this.add(new SaveAsFile(mdm, lp));
    }
}
