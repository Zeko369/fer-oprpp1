package hr.fer.oprpp1.hw08.jnotepadpp.shared;

import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;

/**
 * The type Close helper.
 */
public class CloseHelper {
    /**
     * Close app.
     *
     * @param mdm the mdm
     * @param app the app
     */
    public static void closeApp(MultipleDocumentModel mdm, JFrame app) {
        for (int i = 0; i < mdm.getNumberOfDocuments(); i++) {
            if (!SaveHelper.saveOrCancel(mdm.getDocument(i), mdm, app)) {
                return;
            }
        }

        app.dispose();
        System.exit(0);
    }
}
