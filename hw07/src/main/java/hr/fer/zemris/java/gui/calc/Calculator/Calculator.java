package hr.fer.zemris.java.gui.calc.Calculator;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.UnaryOperator;

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
    private InvertibleButton[] invertibleButtons;

    public Calculator() {
        this(new Point(0, 0));
    }

    public Calculator(Point location) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Java Calculator v1.0");

        this.setLocation(location);
        this.setSize(900, 600);

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
        cp.add(new Button(this.model, 0), new RCPosition(5, 3));
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                cp.add(new Button(this.model, i * 3 + j + 1), new RCPosition(2 + 2 - i, 3 + j));
            }
        }

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

        this.invertibleButtons = this.createInvertibleButtons(cp);

        JCheckBox checkBox = new JCheckBox("Inv");
        checkBox.addActionListener(e -> this.invert(checkBox.isSelected()));
        cp.add(checkBox, new RCPosition(5, 7));
    }

    private InvertibleButton[] createInvertibleButtons(Container cp) {
        InvertibleButton[] btns = new InvertibleButton[8];
        btns[0] = new InvertibleButton("1/x", "1/x", calcUnary(x -> 1 / x), calcUnary(x -> 1 / x));
        btns[1] = new InvertibleButton("log", "10^x", calcUnary(Math::log10), calcUnary(x -> Math.pow(10, x)));
        btns[2] = new InvertibleButton("ln", "e^x", calcUnary(Math::log), calcUnary(Math::exp));
        btns[3] = new InvertibleButton("x^n", "x^(1/n)", () -> {}, () -> {});
        btns[4] = new InvertibleButton("sin", "arcsin", calcUnary(Math::sin), calcUnary(Math::asin));
        btns[5] = new InvertibleButton("cos", "arccos", calcUnary(Math::cos), calcUnary(Math::acos));
        btns[6] = new InvertibleButton("tan", "arctan", calcUnary(Math::tan), calcUnary(Math::atan));
        btns[7] = new InvertibleButton("ctg", "arcctg", calcUnary(x -> Math.tan(1 / x)), calcUnary(x -> Math.atan(1 / x)));

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++) {
                cp.add(btns[i * 4 + j], new RCPosition(j + 2, i + 1));
            }
        }

        return btns;
    }

    private Runnable calcUnary(UnaryOperator<Double> op) {
        return () -> this.model.setValue(op.apply(this.model.getValue()));
    }

    private void invert(boolean invert) {
        Arrays.stream(this.invertibleButtons).forEach(btn -> btn.setInverted(invert));
    }
}
