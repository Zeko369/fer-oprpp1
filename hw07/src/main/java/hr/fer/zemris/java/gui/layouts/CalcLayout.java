package hr.fer.zemris.java.gui.layouts;


import java.awt.*;

import java.util.*;
import java.util.List;

public class CalcLayout implements LayoutManager2 {
    public final static int ROWS = 5;
    public final static int COLUMNS = 7;

    private final Map<Component, RCPosition> children = new HashMap<>();
    private final int spacing;

    private static final RCPosition RESULT_FIELD = new RCPosition(1, 1);

    public CalcLayout(int spacing) {
        if (spacing < 0) {
            throw new CalcLayoutException("Spacing cannot be negative");
        }

        this.spacing = spacing;
    }

    public CalcLayout() {
        this(0);
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints == null) {
            throw new NullPointerException("Constraints cannot be null");
        }

        try {
            RCPosition position = RCPosition.fromObj(constraints);

            if (!position.valid(COLUMNS, ROWS)) {
                System.out.println(position);
                System.out.println(COLUMNS);
                System.out.println(ROWS);
                throw new CalcLayoutException("Invalid position");
            }

            if (this.children.get(comp) != null) {
                throw new CalcLayoutException("Component already added");
            }

            if (position.getRow() == 1 && position.getColumn() > 1 && position.getColumn() < 6) {
                throw new CalcLayoutException("Invalid position (first row has a restricted area 2,3,4,5)");
            }

            this.children.put(comp, position);
        } catch (RCPositionParseException ex) {
            throw new IllegalArgumentException("Invalid constraints: " + ex.getMessage());
        }
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0; // pass
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0; // pass
    }

    @Override
    public void invalidateLayout(Container target) {
        // pass
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        this.children.remove(comp);
    }

    private interface SizeTypeGetter {
        Dimension getSize(Component comp);
    }

    private Dimension calculateDimensions(Container parent, SizeTypeGetter sizeTypeGetter) {
        Insets insets = parent.getInsets();

        List<Dimension> dimensions = Arrays.stream(parent.getComponents())
                .map(comp -> {
                    Dimension d = sizeTypeGetter.getSize(comp);
                    if (this.children.get(comp).equals(RESULT_FIELD)) {
                        d.width = (d.width - this.spacing * 4) / 5;
                    }

                    return d;
                })
                .toList();
        Optional<Dimension> maxH = dimensions.stream().max((a, b) -> (int) (a.getHeight() - b.getHeight()));
        Optional<Dimension> maxW = dimensions.stream().max((a, b) -> (int) (a.getWidth() - b.getWidth()));

        if (maxH.isEmpty() || maxW.isEmpty()) {
            throw new RuntimeException("Error here?");
        }

        return new Dimension(
                Math.round(maxW.get().width * COLUMNS + (COLUMNS - 1) * this.spacing + insets.left + insets.right),
                Math.round(maxH.get().height * ROWS + (ROWS - 1) * this.spacing + insets.top + insets.bottom)
        );
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return calculateDimensions(parent, Component::getPreferredSize);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return calculateDimensions(parent, Component::getMinimumSize);
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return calculateDimensions(target, Component::getMaximumSize);
    }

    private Dimension getCurrentInnerSize(Container parent) {
        Insets insets = parent.getInsets();
        int width = parent.getWidth() - insets.left - insets.right - (COLUMNS - 1) * this.spacing;
        int height = parent.getHeight() - insets.top - insets.bottom - (ROWS - 1) * this.spacing;

        return new Dimension(Math.max(width / COLUMNS, 0), Math.max(height / ROWS, 0));
    }

    private static final int[][] offsets = new int[][]{
            new int[]{0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 1},
            new int[]{1, 0, 0, 0, 0, 0, 1},
            new int[]{1, 0, 0, 1, 0, 0, 1},
            new int[]{1, 0, 1, 0, 1, 0, 1},
            new int[]{1, 1, 0, 1, 0, 1, 1},
            new int[]{1, 1, 1, 0, 1, 1, 1},
    };

    private int getOffsetTillHere(int offsetCount, int index) {
        int width = 0;
        if (offsetCount > 0) {
            for (int i = 0; i < index; i++) {
                width += offsets[offsetCount][i];
            }
        }
        return width;
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        Dimension d = this.getCurrentInnerSize(parent);

        int offsetCount = (parent.getWidth() - this.spacing * (COLUMNS - 1)) % COLUMNS;
        for (Map.Entry<Component, RCPosition> entry : this.children.entrySet()) {
            Component component = entry.getKey();
            RCPosition position = entry.getValue();

            /* (1,1) - (1,5)*/
            if (position.equals(RESULT_FIELD)) {
                int width = d.width * (COLUMNS - 2) + this.spacing * (COLUMNS - 3);
                width += offsets[offsetCount][0];

                component.setBounds(insets.left, insets.top, width, d.height);
            } else {
                component.setBounds(
                        insets.left + (position.getColumn() - 1) * (d.width + this.spacing) + getOffsetTillHere(offsetCount, position.getColumn() - 1),
                        insets.top + (position.getRow() - 1) * (d.height + this.spacing),
                        d.width + offsets[offsetCount][position.getColumn() - 1],
                        d.height
                );
            }
        }
    }
}
