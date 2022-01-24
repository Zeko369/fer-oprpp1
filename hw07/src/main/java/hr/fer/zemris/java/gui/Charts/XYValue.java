package hr.fer.zemris.java.gui.Charts;

/**
 * The type Xy value.
 *
 * @author franzekan
 */
public class XYValue {
    private int x;
    private int y;

    /**
     * Instantiates a new Xy value.
     *
     * @param x the x
     * @param y the y
     */
    public XYValue(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    public void randomise(int minY, int maxY) {
        this.x = (int) (Math.random() * 100);
        this.y = (int) (Math.random() * (maxY - minY)) + minY;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
