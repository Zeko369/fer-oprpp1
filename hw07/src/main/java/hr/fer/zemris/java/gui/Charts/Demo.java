package hr.fer.zemris.java.gui.Charts;

import hr.fer.zemris.java.gui.Charts.shared.LoadData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Demo extends JFrame {
    private final BarChartComponent barChartComponent;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java Demo <path to data file>");
            System.exit(1);
        }

        LoadData loader = new LoadData();
        try {
            loader.getInitialData(args[0]);
        } catch (LoadData.LoadDataException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> new Demo(loader.getChart()).setVisible(true));
    }

    public Demo(BarChart barChart) {
        this.barChartComponent = new BarChartComponent(barChart);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Bar chart");
        this.setSize(800, 600);

        this.initGUI();
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.barChartComponent, BorderLayout.CENTER);
    }
}
