package com.zuhlke.paint.ascii.figure;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code LineStrategy} class allows to draw horizontal or vertical lines.
 * The class could be inherited to support e.g. also curved lines.
 */
public class LineStrategy extends ShapeStrategyBase {

    protected Point start;
    protected Point end;

    protected LineStrategy(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public static LineStrategy create(Point start, Point end) {
        return new LineStrategy(start, end);
    }

    protected boolean isHorizontal() {
        int diff = end.getY() - start.getY();
        return diff == 0;
    }

    protected boolean isVertical() {
        int diff = end.getX() - start.getX();
        return diff == 0;
    }

    /**
     * Draws either a horizontal or a vertical line.
     * Curved lines are not supported at the moment.
     *
     * @return the list of points of the line
     */
    @Override
    public List<Point> draw() {
        List<Point> points = new ArrayList<>();

        if (isHorizontal()) {
            points.addAll(drawHorizontalLine(start, end));
        }
        else if (isVertical()) {
            points.addAll(drawVerticalLine(start, end));
        }
        return points;
    }
}
