package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.FileSystemException;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The type Open document action.
 */
public class OpenDocumentAction extends BaseAction {
    private final JFrame app;

    /**
     * Instantiates a new Open document action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     * @param app the app
     */
    public OpenDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp, JFrame app) {
        super(mdm, lp, "open");
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(lp.getString("open"));
        if (fc.showOpenDialog(this.app) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try {
            this.mdm.loadDocument(fc.getSelectedFile().toPath());
        } catch (FileSystemException | NullPointerException ex) {
            JOptionPane.showMessageDialog(
                    this.app,
                    this.getMessage(ex),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getMessage(RuntimeException ex) {
        if (ex instanceof FileSystemException) {
            return switch (((FileSystemException) ex).getType()) {
                case NOT_READABLE -> "File isn't readable";
                case ERROR -> "Error while opening file";
                case ALREADY_EXISTS -> "";
            };
        }

        if (ex instanceof NullPointerException) {
            return "No path passed";
        }

        throw ex;
    }
}
