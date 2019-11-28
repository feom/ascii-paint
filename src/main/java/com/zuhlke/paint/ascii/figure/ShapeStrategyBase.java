package com.zuhlke.paint.ascii.figure;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ShapeStrategyBase} class represents a base class for shape strategies.
 */
public abstract class ShapeStrategyBase implements ShapeStrategy {

    protected ShapeStrategyBase() {
    }

    protected List<Point> drawHorizontalLine(Point start, Point end) {
        List<Point> points = new ArrayList<>();

        for (int i = start.getX(); i <= end.getX(); i++) {
            Point point = Point.create(i, end.getY());
            points.add(point);
        }

        return points;
    }

    protected List<Point> drawVerticalLine(Point start, Point end) {
        List<Point> points = new ArrayList<>();

        for (int i = start.getY(); i <= end.getY(); i++) {
            Point point = Point.create(end.getX(), i);
            points.add(point);
        }

        return points;
    }
}
