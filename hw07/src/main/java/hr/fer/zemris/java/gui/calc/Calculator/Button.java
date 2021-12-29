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
    }

    public Button(int number, CalcModel model) {
        this();

        this.setFont(getFont().deriveFont(30f));
        this.setText(String.valueOf(number));
        this.addActionListener(e -> model.insertDigit(number));
    }
}
