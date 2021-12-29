package hr.fer.zemris.java.gui.calc.model;

import javax.swing.*;

public class CalcValueListenerImpl implements CalcValueListener {
    private final JLabel label;

    public CalcValueListenerImpl(JLabel label) {
        this.label = label;
    }

    @Override
    public void valueChanged(CalcModel model) {
        this.label.setText(model.toString());
    }
}
