package hr.fer.zemris.java.gui.Charts;

import javax.swing.*;
import java.awt.*;

public class BarChartComponent extends JComponent {
    private BarChart barChart;

    private final int GAP = 10;
    private static final Color COLOR = Color.ORANGE;

    private Dimension dimension;
    private Insets insets = null;

    public BarChartComponent(BarChart barChart) {
        this.barChart = barChart;
    }

    public void setData(BarChart barChart) {
        this.barChart = barChart;
        this.repaint();
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
        int leftPadding = graphics.getFontMetrics().stringWidth(String.valueOf(barChart.getMaxY())) + GAP;
        int bottomPadding = graphics.getFontMetrics().getAscent() + GAP;

        int yCount = (this.barChart.getMaxY() - this.barChart.getMinY()) / this.barChart.getStepY() + 1;
        int xCount = this.barChart.getValues().size();

        int graphWidth = this.dimension.width - this.insets.left - this.insets.right - leftPadding - GAP;
        int graphHeight = this.dimension.height - this.insets.top - this.insets.bottom - bottomPadding - GAP;

        int realWidth = graphWidth / xCount * xCount;
        int realStep = realWidth / xCount;

        this.drawGrid(graphics, leftPadding, graphHeight, realWidth, yCount, xCount);

        for (int i = 0; i < this.barChart.getValues().size(); i++) {
            XYValue value = this.barChart.getValues().get(i);
            int x = (i == 0 ? leftPadding : 0) + i * realStep;
            int tmpHeight = value.getY() * graphHeight / yCount / this.barChart.getStepY();

            graphics.setColor(COLOR);
            graphics.fillRect(x, graphHeight - tmpHeight, (i == 0 ? -leftPadding : 0) + realStep , tmpHeight);
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x, graphHeight - tmpHeight, x, graphHeight);

            graphics.setColor(Color.BLACK);
            graphics.setFont(graphics.getFont().deriveFont(10f));
            graphics.drawString(String.valueOf(value.getX()), x + realStep / 2, graphHeight + GAP);
        }

        graphics.setColor(Color.GRAY);
        graphics.drawLine(leftPadding, graphHeight, leftPadding, this.insets.top);
        graphics.drawLine(leftPadding, graphHeight, this.dimension.width - this.insets.right, graphHeight);

        this.drawArrow(graphics, leftPadding, 0, false);
        this.drawArrow(graphics, this.dimension.width - this.insets.right, graphHeight, true);
    }

    private void drawArrow(Graphics2D graphics, int x, int y, boolean rotated) {
        Polygon polygon = new Polygon();
        int offsetA = GAP / 2;
        int offsetB = GAP;

        polygon.addPoint(x, y);
        polygon.addPoint(x + (!rotated ? -offsetA : -offsetB), y + (!rotated ? offsetB : offsetA));
        polygon.addPoint(x + (!rotated ? offsetA : -offsetB), y + (!rotated ? offsetB : -offsetA));

        graphics.fillPolygon(polygon);
    }

    private void drawGrid(Graphics2D graphics, int x0, int height, int width, int yCount, int xCount) {
        graphics.setColor(Color.YELLOW);

        int xStep = width / xCount;
        int yStep = height / yCount;

        graphics.setFont(graphics.getFont().deriveFont(10f));

        for (int i = 0; i < yCount; i++) {
            graphics.drawLine(x0, height - i * yStep, xStep * xCount, height - i * yStep);

            String tmp = String.valueOf(this.barChart.getStepY() * i + this.barChart.getMinY());
            graphics.setColor(Color.BLACK);
            graphics.drawString(tmp, GAP, height - i * yStep);
            graphics.setColor(Color.YELLOW);
        }

        for (int i = 0; i < xCount; i++) {
            graphics.drawLine(width - i * xStep, height, width - i * xStep, height - yStep * yCount);
        }

        graphics.setColor(Color.BLACK);
    }
}
