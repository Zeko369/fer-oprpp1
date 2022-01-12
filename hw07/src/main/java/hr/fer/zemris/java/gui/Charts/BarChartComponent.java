package hr.fer.zemris.java.gui.Charts;

import javax.swing.*;
import java.awt.*;

public class BarChartComponent extends JComponent {
    private final BarChart barChart;

    private final int GAP = 10;
    private final int HEIGHT = 30;

    private static final Color COLOR = Color.ORANGE;

    private Dimension dimension;
    private Insets insets;

    public BarChartComponent(BarChart barChart) {
        this.barChart = barChart;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.dimension = this.getSize();
        this.insets = this.getInsets();

        drawMain(graphics);
    }

    private void drawMain(Graphics2D graphics) {

    }
}
