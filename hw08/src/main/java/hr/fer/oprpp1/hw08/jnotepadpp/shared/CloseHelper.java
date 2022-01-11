package hr.fer.oprpp1.hw08.jnotepadpp.shared;

import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

public class CloseHelper {
    public static void closeApp(MultipleDocumentModel mdm, JFrame app) {
        app.dispose();
        System.exit(0);
    }
}
