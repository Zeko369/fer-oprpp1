package hr.fer.oprpp1.hw08.jnotepadpp.model;

/**
 * The interface Single document listener.
 */
public interface SingleDocumentListener {
    /**
     * Document modify status updated.
     *
     * @param model the model
     */
    void documentModifyStatusUpdated(SingleDocumentModel model);

    /**
     * Document file path updated.
     *
     * @param model the model
     */
    void documentFilePathUpdated(SingleDocumentModel model);
}
