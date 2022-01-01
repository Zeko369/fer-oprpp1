package hr.fer.zemris.java.gui.calc.Calculator;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }

    private final CalcModel model;

    public Calculator() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Java Calculator v1.0");

        setLocation(80, 80);
        setSize(800, 600);

        this.model = new CalcModelImpl();

        initGUI();
    }

    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new CalcLayout(16));

        // DISPLAY
        JLabel label = new Display(this.model);
        cp.add(label, CalcLayout.RESULT_FIELD);

        JButton btn = new Button("clr");
        btn.addActionListener(e -> this.model.clear());
        cp.add(btn, new RCPosition(1, 7));

        // DIGITS
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                cp.add(new Button(this.model, i * 3 + j + 1), new RCPosition(2 + 2 - i, 3 + j));
            }
        }
        cp.add(new Button(this.model, 0), new RCPosition(5, 3));

        cp.add(new Button("+/-", this.model::swapSign), new RCPosition(5, 4));

        Button dotButton = new Button(".", this.model::insertDecimalPoint);
        dotButton.setEnabled(false);
        this.model.addCalcValueListener(val -> dotButton.setEnabled(val.toString().length() > 0 && !val.toString().contains(".")));
        cp.add(dotButton, new RCPosition(5, 5));

        // OPERATORS
        String[] symbols = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            cp.add(new Button(symbols[i]), new RCPosition(i + 2, 6));
        }

        cp.add(new Button("="), new RCPosition(1, 6));
    }
}
