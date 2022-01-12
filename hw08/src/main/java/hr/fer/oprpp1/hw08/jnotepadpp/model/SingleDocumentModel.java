package hr.fer.oprpp1.hw08.jnotepadpp.model;

import javax.swing.JTextArea;
import java.nio.file.Path;

/**
 * The interface Single document model.
 */
public interface SingleDocumentModel {
    /**
     * Gets text component.
     *
     * @return the text component
     */
    JTextArea getTextComponent();

    /**
     * Gets file path.
     *
     * @return the file path
     */
    Path getFilePath();

    /**
     * Sets file path.
     *
     * @param path the path
     */
    void setFilePath(Path path);

    /**
     * Is modified boolean.
     *
     * @return the boolean
     */
    boolean isModified();

    /**
     * Sets modified.
     *
     * @param modified the modified
     */
    void setModified(boolean modified);

    /**
     * Sets initial text.
     *
     * @param text the text
     */
    void setInitialText(String text);

    /**
     * Add single document listener.
     *
     * @param l the l
     */
    void addSingleDocumentListener(SingleDocumentListener l);

    /**
     * Remove single document listener.
     *
     * @param l the l
     */
    void removeSingleDocumentListener(SingleDocumentListener l);
}
