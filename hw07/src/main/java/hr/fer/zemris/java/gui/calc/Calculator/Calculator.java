package hr.fer.zemris.java.gui.calc.Calculator;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// laptop side (-1000, 600)
// topLeft (0, 0)

public class Calculator extends JFrame {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        Point origin = new Point(0, 0);
        if (args.length == 2) {
            try {
                origin = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                System.err.println("Couldn't parse arguments, using default position");
            }
        }

        Point finalOrigin = origin;
        SwingUtilities.invokeLater(() -> new Calculator(finalOrigin).setVisible(true));
    }

    private final CalcModel model;

    public Calculator() {
        this(new Point(0, 0));
    }

    public Calculator(Point location) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Java Calculator v1.0");

        this.setLocation(location);
        this.setSize(800, 600);

        this.model = new CalcModelImpl();

        this.initGUI();
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
