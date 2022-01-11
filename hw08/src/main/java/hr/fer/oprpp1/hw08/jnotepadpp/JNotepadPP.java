package hr.fer.oprpp1.hw08.jnotepadpp;

import hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar.MenuBar;
import hr.fer.oprpp1.hw08.jnotepadpp.components.Toolbar;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.LocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.listeners.CloseWindowListener;
import hr.fer.oprpp1.hw08.jnotepadpp.listeners.DisableManager;
import hr.fer.oprpp1.hw08.jnotepadpp.model.DefaultMultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.*;

public class JNotepadPP extends JFrame {
    private final MultipleDocumentModel mdm;
    private final FormLocalizationProvider flp;
    private final DisableManager dm;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JNotepadPP().setVisible(true));
    }

    public JNotepadPP() {
        this.mdm = new DefaultMultipleDocumentModel();
        this.flp = new FormLocalizationProvider(LocalizationProvider.getInstance(), this);
        this.dm = new DisableManager();

        this.setLocation(0, 0);
        this.setSize(1000, 1200);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.initGUI();
        this.addWindowListener(new CloseWindowListener(this.mdm, this));
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.mdm.getVisualComponent());

        this.getContentPane().add(new Toolbar(this.mdm, this.flp, this.dm), BorderLayout.PAGE_START);
        this.setJMenuBar(new MenuBar(this.mdm, this.flp, this, this.dm));
    }
}
