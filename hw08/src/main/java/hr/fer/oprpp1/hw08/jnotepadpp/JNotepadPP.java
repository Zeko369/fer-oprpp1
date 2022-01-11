package hr.fer.oprpp1.hw08.jnotepadpp;

import hr.fer.oprpp1.hw08.jnotepadpp.Managers.DisableManager;
import hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.MenuBar;
import hr.fer.oprpp1.hw08.jnotepadpp.components.StatusBar;
import hr.fer.oprpp1.hw08.jnotepadpp.components.Toolbar;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.LocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.listeners.CurrentDocumentChangedListener;
import hr.fer.oprpp1.hw08.jnotepadpp.model.AbstractMultipleDocumentModalListener;
import hr.fer.oprpp1.hw08.jnotepadpp.listeners.CloseWindowListener;
import hr.fer.oprpp1.hw08.jnotepadpp.model.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class JNotepadPP extends JFrame {
    private final MultipleDocumentModel mdm;
    private final FormLocalizationProvider flp;
    private final DisableManager dm;
    private final StatusBar sb;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JNotepadPP().setVisible(true));
    }

    public JNotepadPP() {
        this.setLocation(0, 0);
        this.setSize(1000, 1200);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.mdm = new DefaultMultipleDocumentModel();
        this.flp = new FormLocalizationProvider(LocalizationProvider.getInstance(), this);
        this.dm = new DisableManager();
        this.sb = new StatusBar(this.mdm, this.flp);

        this.addWindowListener(new CloseWindowListener(this.mdm, this));
        this.mdm.addMultipleDocumentListener(new CurrentDocumentChangedListener(this.mdm, this.dm, this.sb, this::setProgramTitle));

        this.initGUI();
        this.setProgramTitle(null);
    }

    private void setProgramTitle(Path file) {
        String name = file == null ? "(unnamed)" : file.toString();
        this.setTitle(String.format("%s - JNotepad++", name));
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.setJMenuBar(new MenuBar(this.mdm, this.flp, this, this.dm));

        this.getContentPane().add(this.mdm.getVisualComponent());

        this.getContentPane().add(new Toolbar(this.mdm, this.flp, this.dm), BorderLayout.PAGE_START);
        this.getContentPane().add(this.sb, BorderLayout.PAGE_END);
    }
}
