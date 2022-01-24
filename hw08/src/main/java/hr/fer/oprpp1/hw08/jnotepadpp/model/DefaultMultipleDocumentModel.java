package hr.fer.oprpp1.hw08.jnotepadpp.model;

import hr.fer.oprpp1.hw08.jnotepadpp.model.enums.FileSystemExceptionType;
import hr.fer.oprpp1.hw08.jnotepadpp.model.util.IconLoader;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * The type Default multiple document model.
 */
public class DefaultMultipleDocumentModel extends JTabbedPane implements MultipleDocumentModel {
    private final List<SingleDocumentModel> documents;
    private int currentIndex = -1;

    private final List<MultipleDocumentListener> listeners;

    private final static IconLoader loader = new IconLoader();
    private final static ImageIcon modifiedIcon = DefaultMultipleDocumentModel.loader.loadIcon("/icons/unmodified.png");
    private final static ImageIcon unmodifiedIcon = DefaultMultipleDocumentModel.loader.loadIcon("/icons/modified.png");

    /**
     * Instantiates a new Default multiple document model.
     */
    public DefaultMultipleDocumentModel() {
        this.listeners = new ArrayList<>();
        this.documents = new ArrayList<>();

        // TODO: Remove
        this.createNewDocument();

        this.addChangeListener(e -> {
            SingleDocumentModel before = this.currentIndex == -1 ? null : this.documents.get(this.currentIndex);
            SingleDocumentModel after = this.documents.get(this.getSelectedIndex());

            this.listeners.forEach(l -> l.currentDocumentChanged(before, after));
            this.currentIndex = this.getSelectedIndex();
        });
    }

    @Override
    public JComponent getVisualComponent() {
        return this;
    }

    private SingleDocumentModel addNewDocument(Path path, String text) {
        SingleDocumentModel doc = new DefaultSingleDocumentModel(path, text);
        this.documents.add(doc);
        this.currentIndex = this.documents.size() - 1;

        String filename = "[untitled]";
        if (path != null) {
            filename = path.getFileName().toString();
        }
        this.addTab(filename, new JScrollPane(doc.getTextComponent()));
        this.setIconAt(this.currentIndex, DefaultMultipleDocumentModel.unmodifiedIcon);
        this.setToolTipTextAt(this.currentIndex, filename);

        doc.addSingleDocumentListener(new SingleDocumentListener() {
            private int getIndex() {
                return DefaultMultipleDocumentModel.this.documents.indexOf(doc);
            }

            @Override
            public void documentModifyStatusUpdated(SingleDocumentModel model) {
                DefaultMultipleDocumentModel.this.setIconAt(
                        this.getIndex(),
                        doc.isModified() ? DefaultMultipleDocumentModel.modifiedIcon : DefaultMultipleDocumentModel.unmodifiedIcon
                );
            }

            @Override
            public void documentFilePathUpdated(SingleDocumentModel model) {
                String text = model.getFilePath() == null ? "unnamed" : String.valueOf(model.getFilePath().getFileName());

                DefaultMultipleDocumentModel.this.setTitleAt(this.getIndex(), text);
                DefaultMultipleDocumentModel.this.setToolTipTextAt(this.getIndex(), text);

                DefaultMultipleDocumentModel.this.listeners.forEach(l -> l.currentDocumentChanged(model, model));
            }
        });

        return doc;
    }

    public SingleDocumentModel createNewDocument() {
        return this.addNewDocument(null, "");
    }

    @Override
    public SingleDocumentModel getCurrentDocument() {
        return this.documents.get(this.currentIndex);
    }

    @Override
    public SingleDocumentModel loadDocument(Path path) throws FileSystemException {
        SingleDocumentModel doc = this.findForPath(Objects.requireNonNull(path));
        if (doc != null) {
            return doc;
        }

        if (!Files.isReadable(path)) {
            throw new FileSystemException(FileSystemExceptionType.NOT_READABLE);
        }

        try {
            return this.addNewDocument(path, Files.readString(path, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new FileSystemException(FileSystemExceptionType.ERROR);
        }
    }

    @Override
    public void saveDocument(SingleDocumentModel model, Path newPath) throws FileSystemException {
        SingleDocumentModel doc = this.findForPath(newPath);
        if (doc != null) {
            throw new FileSystemException(FileSystemExceptionType.ALREADY_EXISTS);
        }

        model.setFilePath(newPath == null ? model.getFilePath() : newPath);
        model.setInitialText(model.getTextComponent().getText());

        this.setTitleAt(this.getSelectedIndex(), model.getFilePath().toAbsolutePath().getFileName().toString());

        try {
            Files.writeString(model.getFilePath(), model.getTextComponent().getText(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileSystemException(FileSystemExceptionType.ERROR);
        }
    }

    @Override
    public void closeDocument(SingleDocumentModel model) {
        switch (this.documents.size()) {
            case 0:
                return;
            case 1:
                this.createNewDocument();

                this.listeners.forEach(l -> l.documentRemoved(this.documents.get(0)));
                this.listeners.forEach(l -> l.currentDocumentChanged(this.documents.get(0), this.documents.get(1)));
                this.removeTabAt(0);
                this.documents.remove(0);
                return;
            default:
                for (int i = 0; i < this.documents.size(); i++) {
                    if (this.documents.get(i).equals(model)) {
                        this.removeTabAt(i);
                        this.listeners.forEach(l -> l.documentRemoved(model));
                        this.documents.remove(i);

                        return;
                    }
                }
        }

        throw new RuntimeException("Error closing document");
    }

    @Override
    public void addMultipleDocumentListener(MultipleDocumentListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeMultipleDocumentListener(MultipleDocumentListener l) {
        this.listeners.remove(l);
    }

    @Override
    public int getNumberOfDocuments() {
        return this.documents.size();
    }

    @Override
    public SingleDocumentModel getDocument(int index) {
        return this.documents.get(index);
    }

    @Override
    public SingleDocumentModel findForPath(Path path) {
        return this.documents.stream()
                .filter(d -> path.equals(d.getFilePath()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getIndexOfDocument(SingleDocumentModel doc) {
        return this.currentIndex;
    }

    @Override
    public Iterator<SingleDocumentModel> iterator() {
        return this.documents.iterator();
    }
}
