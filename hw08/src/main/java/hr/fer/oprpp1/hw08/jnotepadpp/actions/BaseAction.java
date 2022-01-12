package hr.fer.oprpp1.hw08.jnotepadpp.actions;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.utils.LocalizableAction;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

/**
 * The type Base action.
 */
public abstract class BaseAction extends LocalizableAction {
    /**
     * The Mdm.
     */
    protected final MultipleDocumentModel mdm;
    /**
     * The Lp.
     */
    protected final ILocalizationProvider lp;

    /**
     * Instantiates a new Base action.
     *
     * @param mdm the mdm
     * @param lp  the lp
     * @param key the key
     */
    public BaseAction(MultipleDocumentModel mdm, ILocalizationProvider lp, String key) {
        super(key, lp);

        this.mdm = mdm;
        this.lp = lp;
    }

    /**
     * Instantiates a new Base action.
     *
     * @param lp  the lp
     * @param key the key
     */
    public BaseAction(ILocalizationProvider lp, String key) {
        this(null, lp, key);
    }

    /**
     * Is disableable boolean.
     *
     * @return the boolean
     */
    public boolean isDisableable() {
        return false;
    }
}
