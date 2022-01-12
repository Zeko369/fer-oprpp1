package hr.fer.oprpp1.hw08.jnotepadpp.shared;

import hr.fer.oprpp1.hw08.jnotepadpp.model.FileSystemException;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.model.SingleDocumentModel;

import javax.swing.*;
import java.nio.file.Path;

public class SaveHelper {
    public static void save(SingleDocumentModel model, MultipleDocumentModel mdm, JFrame app) {
        SaveHelper.saveCurrent(mdm, app, false);
    }

    public static void saveAs(SingleDocumentModel model, MultipleDocumentModel mdm, JFrame app) {
        SaveHelper.saveCurrent(mdm, app, true);
    }

    public static boolean saveOrCancel(SingleDocumentModel model, MultipleDocumentModel mdm, JFrame app) {
        if (!model.isModified()) {
            return true;
        }

        int dialogResult = JOptionPane.showConfirmDialog(
                app,
                "Do you want to save this",
                "Warning",
                JOptionPane.YES_NO_OPTION
        );

        switch (dialogResult) {
            case JOptionPane.CANCEL_OPTION:
            case JOptionPane.CLOSED_OPTION:
                return false;
            case JOptionPane.YES_OPTION:
                SaveHelper.save(model, mdm, app);
                mdm.closeDocument(mdm.getCurrentDocument());
        }

        return true;
    }

    public static void saveCurrent(MultipleDocumentModel mdm, JFrame app, boolean saveAs) {
        Path path = null;
        if (mdm.getCurrentDocument().getFilePath() == null || saveAs) {
            String title = String.format("Save%s document", saveAs ? " as" : "");
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle(title);
            if (jfc.showSaveDialog(app) != JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(app, "Nothing saved", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            path = jfc.getSelectedFile().toPath();
        }

        try {
            if (path == null) {
                throw new NullPointerException("Path is null");
            }

            mdm.saveDocument(mdm.getCurrentDocument(), path);

            JOptionPane.showMessageDialog(app, "File successfully saved", "File saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (NullPointerException | FileSystemException ex) {
            JOptionPane.showMessageDialog(
                    app,
                    SaveHelper.getSaveErrorMessage(ex),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static String getSaveErrorMessage(RuntimeException ex) {
        if (ex instanceof FileSystemException) {
            return switch (((FileSystemException) ex).getType()) {
                case ALREADY_EXISTS -> "File already exists";
                case NOT_READABLE -> "Can't save";
                case ERROR -> "Error saving";
            };
        }

        if (ex instanceof NullPointerException) {
            return "Path is null";
        }

        throw ex;
    }
}
