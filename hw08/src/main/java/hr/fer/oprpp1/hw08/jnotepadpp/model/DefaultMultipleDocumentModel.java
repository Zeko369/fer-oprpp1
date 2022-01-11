package hr.fer.oprpp1.hw08.jnotepadpp.model;

import hr.fer.oprpp1.hw08.jnotepadpp.model.util.IconLoader;

import javax.swing.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultMultipleDocumentModel extends JTabbedPane implements MultipleDocumentModel {
    private final List<SingleDocumentModel> documents;
    private int currentIndex = -1;

    private final List<MultipleDocumentListener> listeners;

    private final static IconLoader loader = new IconLoader();
    private final static ImageIcon modifiedIcon = DefaultMultipleDocumentModel.loader.loadIcon("/icons/modified.png");
    private final static ImageIcon unmodifiedIcon = DefaultMultipleDocumentModel.loader.loadIcon("/icons/unmodified.png");

    public DefaultMultipleDocumentModel() {
        this.listeners = new ArrayList<>();
        this.documents = new ArrayList<>();


        // TODO: Remove
        this.createNewDocument();

        this.addChangeListener(e -> {
            SingleDocumentModel before = this.currentIndex == -1 ? null : this.documents.get(this.currentIndex);
            SingleDocumentModel after = this.documents.get(this.getSelectedIndex());

            this.listeners.forEach(l -> l.currentDocumentChanged(before, after));
        });
    }

    @Override
    public JComponent getVisualComponent() {
        return this;
    }

    @Override
    public SingleDocumentModel createNewDocument() {
        SingleDocumentModel doc = new DefaultSingleDocumentModel(null, "");
        this.documents.add(doc);
        this.currentIndex = this.documents.size() - 1;

        String filename = "[untitled]";
        this.addTab(filename, new JScrollPane(doc.getTextComponent()));
        this.setIconAt(this.currentIndex, DefaultMultipleDocumentModel.unmodifiedIcon);

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

    @Override
    public SingleDocumentModel getCurrentDocument() {
        return this.documents.get(this.currentIndex);
    }

    @Override
    public SingleDocumentModel loadDocument(Path path) {
        return null;
    }

    @Override
    public void saveDocument(SingleDocumentModel model, Path newPath) {

    }

    @Override
    public void closeDocument(SingleDocumentModel model) {
        int index = this.documents.indexOf(model);
        if (index == -1) {
            return;
        }

        this.remove(index);
        this.documents.remove(index);

        if (this.currentIndex == index) {
            this.currentIndex = 0;
        } else if (this.currentIndex > index) {
            this.currentIndex--;
        }
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
                .filter(d -> d.getFilePath().equals(path))
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
