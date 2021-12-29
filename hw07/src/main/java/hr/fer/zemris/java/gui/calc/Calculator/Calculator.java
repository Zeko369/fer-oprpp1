package hr.fer.zemris.java.gui.calc.Calculator;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;
import hr.fer.zemris.java.gui.calc.model.CalcValueListenerImpl;
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

        JLabel label = new JLabel("0");
        cp.add(label, CalcLayout.RESULT_FIELD);
        this.model.addCalcValueListener(new CalcValueListenerImpl(label));

        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                int tmp = i * 3 + j + 1;
                cp.add(new Button(tmp, this.model), new RCPosition(2 + 2 - i, 3 + j));
            }
        }
        cp.add(new Button(0, this.model), new RCPosition(5, 3));
    }
}
