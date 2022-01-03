package hr.fer.zemris.java.gui.calc.Calculator;

import hr.fer.zemris.java.gui.calc.model.CalcModel;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private static final Color BG_COLOR = new Color(114, 159, 207);

    public Button() {
        this.setOpaque(true);
        this.setBackground(BG_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setFont(getFont().deriveFont(30f));
    }

    public Button(String text) {
        this();
        this.setText(text);
    }

    public Button(String text, Runnable action) {
        this(text);
        this.addActionListener(e -> action.run());
    }

    // Digit constructor
    public Button(CalcModel model, int number) {
        this(String.valueOf(number), () -> model.insertDigit(number));
    }
}
