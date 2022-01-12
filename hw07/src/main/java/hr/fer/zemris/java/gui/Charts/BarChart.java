package hr.fer.zemris.java.gui.Charts;

import java.util.List;

public class BarChart {
    private final List<XYValue> values;

    private final String xDescription;

    private final String yDescription;

    private final int minY;
    private final int maxY;
    private final int stepY;

    public BarChart(List<XYValue> values, String xDescription, String yDescription, int minY, int maxY, int stepY) {
        this.values = values;
        this.xDescription = xDescription;
        this.yDescription = yDescription;

        this.minY = minY;

        while ((maxY - minY) % stepY != 0)
            maxY++;

        this.maxY = maxY;
        this.stepY = stepY;

        if (this.minY < 0) {
            throw new IllegalArgumentException("Min has to be greater than 0.");
        }

        if (this.minY > this.maxY) {
            throw new IllegalArgumentException("Min has to be smaller than max.");
        }

        values.forEach(value -> {
            if (value.getY() > this.maxY || value.getY() < this.minY) {
                throw new IllegalArgumentException("Value has to be between min and max.");
            }
        });
    }

    public List<XYValue> getValues() {
        return values;
    }

    public String getxDescription() {
        return xDescription;
    }

    public String getyDescription() {
        return yDescription;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getStepY() {
        return stepY;
    }
}
