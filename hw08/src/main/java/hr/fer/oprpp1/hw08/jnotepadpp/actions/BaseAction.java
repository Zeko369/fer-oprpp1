package hr.fer.oprpp1.hw08.jnotepadpp.actions;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.utils.LocalizableAction;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

public abstract class BaseAction extends LocalizableAction {
    protected final MultipleDocumentModel mdm;

    public BaseAction(MultipleDocumentModel mdm, ILocalizationProvider lp, String key) {
        super(key, lp);
        this.mdm = mdm;
    }

    public BaseAction(ILocalizationProvider lp, String key) {
        this(null, lp, key);
    }

    public boolean isDisableable() {
        return false;
    }
}
