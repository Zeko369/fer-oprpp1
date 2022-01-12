package hr.fer.oprpp1.hw08.jnotepadpp.model;

import javax.swing.JComponent;
import java.nio.file.Path;

public interface MultipleDocumentModel extends Iterable<SingleDocumentModel> {
    JComponent getVisualComponent();

    SingleDocumentModel createNewDocument();

    SingleDocumentModel getCurrentDocument();

    SingleDocumentModel loadDocument(Path path) throws FileSystemException;

    void saveDocument(SingleDocumentModel model, Path newPath) throws FileSystemException;

    void closeDocument(SingleDocumentModel model);

    void addMultipleDocumentListener(MultipleDocumentListener l);

    void removeMultipleDocumentListener(MultipleDocumentListener l);

    int getNumberOfDocuments();

    SingleDocumentModel getDocument(int index);

    SingleDocumentModel findForPath(Path path); //null, if no such model exists

    int getIndexOfDocument(SingleDocumentModel doc); //-1 if not present
}
