package hr.fer.oprpp1.hw08.jnotepadpp.Managers;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;

import java.util.ArrayList;
import java.util.List;

public class DisableManager {
    private final List<BaseAction> actions = new ArrayList<>();

    public void addAll(List<BaseAction> actions) {
        this.actions.addAll(actions);
    }

    public void trigger(boolean state) {
        this.actions.forEach(action -> action.setEnabled(state));
    }
}
