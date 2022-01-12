package hr.fer.oprpp1.hw08.jnotepadpp.listeners;

import hr.fer.oprpp1.hw08.jnotepadpp.managers.DisableManager;
import hr.fer.oprpp1.hw08.jnotepadpp.components.StatusBar;
import hr.fer.oprpp1.hw08.jnotepadpp.model.AbstractMultipleDocumentModalListener;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;
import hr.fer.oprpp1.hw08.jnotepadpp.model.SingleDocumentModel;

import javax.swing.event.CaretListener;
import java.nio.file.Path;

/**
 * The type Current document changed listener.
 */
public class CurrentDocumentChangedListener extends AbstractMultipleDocumentModalListener {
    /**
     * The interface Set file title.
     */
    public interface SetFileTitle {
        /**
         * Set.
         *
         * @param file the file
         */
        void set(Path file);
    }

    private final MultipleDocumentModel mdm;
    private final DisableManager dm;
    private final StatusBar sb;
    private final SetFileTitle sft;

    /**
     * Instantiates a new Current document changed listener.
     *
     * @param mdm the mdm
     * @param dm  the dm
     * @param sb  the sb
     * @param sft the sft
     */
    public CurrentDocumentChangedListener(MultipleDocumentModel mdm, DisableManager dm, StatusBar sb, SetFileTitle sft) {
        this.mdm = mdm;
        this.dm = dm;
        this.sb = sb;
        this.sft = sft;

        this.currentDocumentChanged(null, mdm.getCurrentDocument());
    }

    private boolean shouldDisabled(SingleDocumentModel model) {
        return model.getTextComponent().getSelectedText() != null;
    }

    private final CaretListener caretListener = e -> {
        SingleDocumentModel current = CurrentDocumentChangedListener.this.mdm.getCurrentDocument();

        CurrentDocumentChangedListener.this.sb.render(current.getTextComponent());
        CurrentDocumentChangedListener.this.dm.trigger(this.shouldDisabled(current));
    };

    @Override
    public void currentDocumentChanged(SingleDocumentModel previousModel, SingleDocumentModel currentModel) {
        if (previousModel != null) {
            previousModel.getTextComponent().removeCaretListener(this.caretListener);
        }

        currentModel.getTextComponent().addCaretListener(this.caretListener);

        this.sb.render(currentModel.getTextComponent());
        this.dm.trigger(this.shouldDisabled(currentModel));
        this.sft.set(currentModel.getFilePath());
    }
}
