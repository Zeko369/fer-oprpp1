package hr.fer.oprpp1.hw08.jnotepadpp.model;

import javax.swing.JComponent;
import java.nio.file.Path;

/**
 * The interface Multiple document model.
 */
public interface MultipleDocumentModel extends Iterable<SingleDocumentModel> {
    /**
     * Gets visual component.
     *
     * @return the visual component
     */
    JComponent getVisualComponent();

    /**
     * Create new document single document model.
     *
     * @return the single document model
     */
    SingleDocumentModel createNewDocument();

    /**
     * Gets current document.
     *
     * @return the current document
     */
    SingleDocumentModel getCurrentDocument();

    /**
     * Load document single document model.
     *
     * @param path the path
     * @return the single document model
     * @throws FileSystemException the file system exception
     */
    SingleDocumentModel loadDocument(Path path) throws FileSystemException;

    /**
     * Save document.
     *
     * @param model   the model
     * @param newPath the new path
     * @throws FileSystemException the file system exception
     */
    void saveDocument(SingleDocumentModel model, Path newPath) throws FileSystemException;

    /**
     * Close document.
     *
     * @param model the model
     */
    void closeDocument(SingleDocumentModel model);

    /**
     * Add multiple document listener.
     *
     * @param l the l
     */
    void addMultipleDocumentListener(MultipleDocumentListener l);

    /**
     * Remove multiple document listener.
     *
     * @param l the l
     */
    void removeMultipleDocumentListener(MultipleDocumentListener l);

    /**
     * Gets number of documents.
     *
     * @return the number of documents
     */
    int getNumberOfDocuments();

    /**
     * Gets document.
     *
     * @param index the index
     * @return the document
     */
    SingleDocumentModel getDocument(int index);

    /**
     * Find for path single document model.
     *
     * @param path the path
     * @return the single document model
     */
    SingleDocumentModel findForPath(Path path); //null, if no such model exists

    /**
     * Gets index of document.
     *
     * @param doc the doc
     * @return the index of document
     */
    int getIndexOfDocument(SingleDocumentModel doc); //-1 if not present
}
