package hr.fer.oprpp1.hw08.jnotepadpp.model;

import javax.swing.JTextArea;
import java.nio.file.Path;

public interface SingleDocumentModel {
    JTextArea getTextComponent();

    Path getFilePath();

    void setFilePath(Path path);

    boolean isModified();

    void setModified(boolean modified);

    void setInitialText(String text);

    void addSingleDocumentListener(SingleDocumentListener l);

    void removeSingleDocumentListener(SingleDocumentListener l);
}
