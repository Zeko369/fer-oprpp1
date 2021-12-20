package hr.fer.zemris.java.gui.demo;

import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

import javax.swing.*;
import java.awt.*;

public class DemoFrame1 extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DemoFrame1().setVisible(true));
    }

    public DemoFrame1() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.initGUI();
        this.pack();
    }

    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new CalcLayout(3));
        cp.add(l("tekst 1"), "1,1");
        cp.add(l("tekst 2"), "2,3");
        cp.add(l("tekst stvarno najdulji"), new RCPosition(2, 7));
        cp.add(l("tekst kraći"), new RCPosition(4, 2));
        cp.add(l("tekst srednji"), new RCPosition(4, 5));
        cp.add(l("tekst"), new RCPosition(4, 7));
    }

    private JLabel l(String text) {
        JLabel l = new JLabel(text);
        l.setBackground(Color.YELLOW);
        l.setOpaque(true);
        return l;
    }
}
