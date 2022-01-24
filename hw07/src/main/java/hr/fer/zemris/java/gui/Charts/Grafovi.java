package hr.fer.zemris.java.gui.Charts;

import hr.fer.zemris.java.gui.Charts.shared.LoadData;

import javax.swing.*;
import java.awt.*;

public class Grafovi extends JFrame {
    private final LoadData[] loaders;
    private final BarChartComponent[] barChartGraphs;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Demo <path to data file>");
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> new Grafovi(args[0]).setVisible(true));
    }

    public Grafovi(String path) {
        this.loaders = new LoadData[4];
        for (int i = 0; i < 4; i++) {
            this.loaders[i] = new LoadData();
            this.loaders[i].getInitialData(path);
        }

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Bar chart");
        this.setSize(800, 600);

        this.barChartGraphs = new BarChartComponent[4];

        this.initGUI();
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());

        JPanel graphs = new JPanel(new GridLayout(2, 2));
        graphs.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            this.barChartGraphs[i] = new BarChartComponent(this.loaders[i].getChart());
            graphs.add(this.barChartGraphs[i]);
        }
        this.getContentPane().add(graphs, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            JButton btn = new JButton("Graph " + (i + 1));

            int finalI = i;
            btn.addActionListener((e) -> {
                this.loaders[finalI].randomise();
                this.barChartGraphs[finalI].setData(this.loaders[finalI].getChart());
            });

            buttons.add(btn);
        }

        this.getContentPane().add(buttons, BorderLayout.PAGE_END);
    }
}
