package hr.fer.zemris.java.gui.layouts;


import java.awt.Component;
import java.awt.LayoutManager2;
import java.awt.Dimension;
import java.awt.Container;

import java.util.*;

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
            throw new CalcLayoutException("Invalid constraints: " + ex.getMessage());
        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return null;
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

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        List<Dimension> dimensions = Arrays.stream(parent.getComponents()).map(Component::getPreferredSize).toList();
        Optional<Dimension> maxH = dimensions.stream().min((a, b) -> (int) (a.getHeight() - b.getHeight()));
        Optional<Dimension> maxW = dimensions.stream().min((a, b) -> (int) (a.getWidth() - b.getWidth()));

        if (maxH.isEmpty() || maxW.isEmpty()) {
            throw new RuntimeException("Error here?");
        }

        return new Dimension(maxW.get().width * COLUMNS + spacing * (COLUMNS - 1), maxH.get().height * ROWS + spacing * (ROWS - 1));
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
        Dimension tmp = this.preferredLayoutSize(parent);

        int width = tmp.width / COLUMNS;
        int height = tmp.height / ROWS;

        for (Map.Entry<Component, RCPosition> entry : this.children.entrySet()) {
            Component component = entry.getKey();
            RCPosition position = entry.getValue();

            /* (1,1) - (1,5)*/
            if (position.equals(RESULT_FIELD)) {
                component.setBounds(0, 0, width * 5, height);
            } else {
                component.setBounds(width * position.getColumn() + (position.getColumn() - 1) * spacing, height * position.getRow() + (position.getRow() - 1) * spacing, width, height);
            }
        }
    }
}
