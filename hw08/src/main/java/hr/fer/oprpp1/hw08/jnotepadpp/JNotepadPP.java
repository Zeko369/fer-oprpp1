package hr.fer.oprpp1.hw08.jnotepadpp;

import hr.fer.oprpp1.hw08.jnotepadpp.model.DefaultMultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.*;

public class JNotepadPP extends JFrame {
    private final MultipleDocumentModel mdm;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JNotepadPP().setVisible(true));
    }

    public JNotepadPP() {
        this.mdm = new DefaultMultipleDocumentModel();

        this.setLocation(0, 0);
        this.setSize(1000, 1200);

        this.initGUI();
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.mdm.getVisualComponent());
    }
}

