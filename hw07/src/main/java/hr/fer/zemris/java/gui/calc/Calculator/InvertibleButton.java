package hr.fer.zemris.java.gui.calc.Calculator;

public class InvertibleButton extends Button {
    private final String defaultLabel;
    private final String invertedLabel;

    private final Runnable defaultAction;
    private final Runnable invertedAction;

    private boolean inverted = false;

    public InvertibleButton(String label, String invertedLabel, Runnable action, Runnable invertedAction) {
        super(label, action);

        this.defaultAction = action;
        this.invertedAction = invertedAction;

        this.defaultLabel = label;
        this.invertedLabel = invertedLabel;

        this.addActionListener(e -> {
            if (this.inverted) {
                this.invertedAction.run();
            } else {
                this.defaultAction.run();
            }
        });
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
        this.setText(inverted ? this.invertedLabel : this.defaultLabel);
    }
}
