package hr.fer.oprpp1.hw08.jnotepadpp.managers;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Disable manager.
 */
public class DisableManager {
    private final List<BaseAction> actions = new ArrayList<>();

    /**
     * Add all.
     *
     * @param actions the actions
     */
    public void addAll(List<BaseAction> actions) {
        this.actions.addAll(actions);
    }

    /**
     * Trigger.
     *
     * @param state the state
     */
    public void trigger(boolean state) {
        this.actions.forEach(action -> action.setEnabled(state));
    }
}
