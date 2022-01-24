package hr.fer.zemris.java.gui.Charts.shared;

import hr.fer.zemris.java.gui.Charts.BarChart;
import hr.fer.zemris.java.gui.Charts.Demo;
import hr.fer.zemris.java.gui.Charts.XYValue;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class LoadData {
    private BarChart chart;

    public static class LoadDataException extends RuntimeException {
        public LoadDataException(String message) {
            super(message);
        }
    }

    public BarChart getChart() {
        return chart;
    }

    public void getInitialData(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new LoadDataException("Error opening file");
        }

        if (lines.size() < 5) {
            throw new LoadDataException("Not enough lines");
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
            this.chart = new BarChart(values, xDescription, yDescription, minY, maxY, step);
        } catch (IllegalArgumentException e) {
            throw new LoadDataException("Invalid data");
        }
    }

    public void randomise() {
        this.chart.randomise();
    }
}
