package hr.fer.oprpp1.hw08.jnotepadpp.components;

import hr.fer.oprpp1.hw08.jnotepadpp.Managers.DisableManager;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.InvertCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.LowerCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Case.UpperCase;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard.CopyAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard.CutAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.Edit.Clipboard.PasteAction;
import hr.fer.oprpp1.hw08.jnotepadpp.actions.File.*;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJToolBar;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends LocalizedJToolBar {
    private final List<BaseAction> actions = new ArrayList<>();

    public Toolbar(MultipleDocumentModel mdm, ILocalizationProvider lp, DisableManager dm) {
        super("toolbar", lp);

        this.setFloatable(true);

        this.addAction(new NewDocumentAction(mdm, lp));
        this.addAction(new OpenDocumentAction(mdm, lp));
        this.addAction(new SaveDocumentAction(mdm, lp));
        this.addAction(new SaveAsDocumentAction(mdm, lp));
        this.addAction(new CloseDocumentAction(mdm, lp));

        this.addSeparator();

        this.addAction(new CutAction(mdm, lp));
        this.addAction(new CopyAction(mdm, lp));
        this.addAction(new PasteAction(mdm, lp));

        this.addSeparator();

        this.addAction(new InvertCase(mdm, lp));
        this.addAction(new UpperCase(mdm, lp));
        this.addAction(new LowerCase(mdm, lp));

        dm.addAll(this.actions);
    }

    private void addAction(BaseAction action) {
        if (action.isDisableable()) {
            this.actions.add(action);
        }

        this.add(new JButton(action));
    }
}
