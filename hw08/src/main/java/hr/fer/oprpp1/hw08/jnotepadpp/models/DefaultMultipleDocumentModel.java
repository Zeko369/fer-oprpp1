package hr.fer.oprpp1.hw08.jnotepadpp.models;

import javax.swing.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultMultipleDocumentModel extends JTabbedPane implements MultipleDocumentModel {
    private final List<SingleDocumentModel> documents;
    private int currentIndex = -1;

    private final List<MultipleDocumentListener> listeners;

    public DefaultMultipleDocumentModel() {
        this.listeners = new ArrayList<>();
        this.documents = new ArrayList<>();

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
