package hr.fer.zemris.java.gui.Charts;

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

        List<String> lines = Files.readAllLines(Paths.get(args[0]));

        if (lines.size() < 5) {
            System.err.println("Expected at least 5 lines in data file");
            System.exit(1);
        }

        String xDescription = lines.get(0);
        String yDescription = lines.get(1);

        List<XYValue> values = Arrays.stream(lines.get(2).split(" "))
                .map(s -> s.split(","))
                .map(arr -> new XYValue(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])))
                .toList();

        int minY = Integer.parseInt(lines.get(3));
        int maxY = Integer.parseInt(lines.get(4));
        int step = Integer.parseInt(lines.get(5));

        try {
            BarChart chart = new BarChart(values, xDescription, yDescription, minY, maxY, step);
            SwingUtilities.invokeLater(() -> new Demo(chart).setVisible(true));
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading data from file");
            System.err.println(e.getMessage());
        }
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
