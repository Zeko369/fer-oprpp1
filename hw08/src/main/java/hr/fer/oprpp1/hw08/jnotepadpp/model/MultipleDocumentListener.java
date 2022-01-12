package hr.fer.oprpp1.hw08.jnotepadpp.model;

/**
 * The interface Multiple document listener.
 */
public interface MultipleDocumentListener {
    /**
     * Current document changed.
     *
     * @param previousModel the previous model
     * @param currentModel  the current model
     */
    void currentDocumentChanged(SingleDocumentModel previousModel,
                                SingleDocumentModel currentModel);

    /**
     * Document added.
     *
     * @param model the model
     */
    void documentAdded(SingleDocumentModel model);

    /**
     * Document removed.
     *
     * @param model the model
     */
    void documentRemoved(SingleDocumentModel model);
}
