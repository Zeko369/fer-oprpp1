package hr.fer.oprpp1.hw08.jnotepadpp.listeners;

import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.shared.CloseHelper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseWindowListener extends WindowAdapter {
    private final MultipleDocumentModel mdm;
    private final JFrame app;

    public CloseWindowListener(MultipleDocumentModel mdm, JFrame app) {
        this.mdm = mdm;
        this.app = app;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        CloseHelper.closeApp(this.mdm, this.app);
    }
}
