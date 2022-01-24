package hr.fer.oprpp1.hw08.jnotepadpp.model;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Default single document model.
 */
public class DefaultSingleDocumentModel implements SingleDocumentModel {
    private final JTextArea textComponent;

    private String initContent;
    private Path path;
    private boolean isModified = true;

    private final List<SingleDocumentListener> listeners;

    /**
     * Instantiates a new Default single document model.
     *
     * @param path    the path
     * @param content the content
     */
    public DefaultSingleDocumentModel(Path path, String content) {
        this.listeners = new ArrayList<>();
        this.path = path;
        this.initContent = content;

        this.textComponent = new JTextArea(content);
        this.textComponent.getDocument().addDocumentListener(new DocumentListener() {
            private boolean hasChanges() {
                return !DefaultSingleDocumentModel.this.initContent.equals(DefaultSingleDocumentModel.this.textComponent.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                DefaultSingleDocumentModel.this.setModified(this.hasChanges());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                DefaultSingleDocumentModel.this.setModified(this.hasChanges());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                DefaultSingleDocumentModel.this.setModified(this.hasChanges());
            }
        });
    }

    @Override
    public void setInitialText(String initContent) {
        this.initContent = initContent;
        this.setModified(false);
    }

    @Override
    public JTextArea getTextComponent() {
        return this.textComponent;
    }

    @Override
    public Path getFilePath() {
        return this.path;
    }

    @Override
    public void setFilePath(Path path) {
        this.path = path;
    }

    @Override
    public boolean isModified() {
        return this.isModified;
    }

    @Override
    public void setModified(boolean modified) {
        if (this.isModified != modified) {
            this.listeners.forEach(l -> l.documentModifyStatusUpdated(this));
        }

        this.isModified = modified;
    }


    @Override
    public void addSingleDocumentListener(SingleDocumentListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeSingleDocumentListener(SingleDocumentListener l) {
        this.listeners.remove(l);
    }
}
