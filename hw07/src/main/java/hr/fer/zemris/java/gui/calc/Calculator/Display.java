package hr.fer.zemris.java.gui.calc.Calculator;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcValueListenerImpl;

import javax.swing.*;
import java.awt.*;

public class Display extends JLabel {
    private static final Color BG_COLOR = new Color(255, 255, 0);

    public Display(CalcModel model) {
        this.setOpaque(true);
        this.setBackground(BG_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setFont(getFont().deriveFont(30f));
        this.setHorizontalAlignment(SwingConstants.RIGHT);

        this.setText(model.toString());

        model.addCalcValueListener(new CalcValueListenerImpl(this));
    }
}
