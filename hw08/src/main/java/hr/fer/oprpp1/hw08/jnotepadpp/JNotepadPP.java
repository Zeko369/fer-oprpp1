package hr.fer.oprpp1.hw08.jnotepadpp;

import hr.fer.oprpp1.hw08.jnotepadpp.components.MenuBar;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.LocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.DefaultMultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.*;

public class JNotepadPP extends JFrame {
    private final MultipleDocumentModel mdm;
    private final FormLocalizationProvider flp;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JNotepadPP().setVisible(true));
    }

    public JNotepadPP() {
        this.mdm = new DefaultMultipleDocumentModel();
        this.flp = new FormLocalizationProvider(LocalizationProvider.getInstance(), this);

        this.setLocation(0, 0);
        this.setSize(1000, 1200);

        this.initGUI();
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.mdm.getVisualComponent());

        this.setJMenuBar(new MenuBar(this.mdm, this.flp));
    }
}

