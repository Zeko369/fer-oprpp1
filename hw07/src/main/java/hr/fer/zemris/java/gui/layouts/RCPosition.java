package hr.fer.zemris.java.gui.layouts;

public class RCPosition {
    private final int row;
    private final int column;

    public RCPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static RCPosition parseString(String text) {
        if (text == null || text.isEmpty()) {
            throw new RCPositionParseException("Invalid RCPosition argument string");
        }

        String[] parts = text.split(",");
        if (parts.length != 2) {
            throw new RCPositionParseException("Argument must be in format \"row,column\"");
        }

        try {
            return new RCPosition(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
        } catch (NumberFormatException ex) {
            throw new RCPositionParseException("One of the numbers in RCPosition is not an integer");
        }
    }

    public static RCPosition fromObj(Object obj) {
        if (obj instanceof RCPosition) {
            return (RCPosition) obj;
        } else if (obj instanceof String) {
            return RCPosition.parseString((String) obj);
        }

        throw new RCPositionParseException("Invalid constraint type");
    }

    public boolean valid(int maxX, int maxY) {
        if (this.row < 1 || this.column < 1) {
            return false;
        }

        return this.column <= maxX && this.row <= maxY;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCPosition other = (RCPosition) o;

        if (this.row != other.row) return false;
        return this.column == other.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.row, this.column);
    }
}
