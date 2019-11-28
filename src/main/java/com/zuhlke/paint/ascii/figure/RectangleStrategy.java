package com.zuhlke.paint.ascii.figure;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RectangleStrategy} class allows to draw rectangles.
 */
public class RectangleStrategy extends ShapeStrategyBase {

    private Point upperLeft;
    private Point lowerRight;

    private RectangleStrategy(Point upperLeft, Point lowerRight) {
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }

    /**
     * Draws a rectangle from upper left point to lower right point.
     *
     * @return the list of points of the rectangle
     */
    @Override
    public List<Point> draw() {
        List<Point> points = new ArrayList<>();

        Point upperRight = Point.create(lowerRight.getX(), upperLeft.getY());
        Point lowerLeft = Point.create(upperLeft.getX(), lowerRight.getY());

        points.addAll(drawHorizontalLine(upperLeft, upperRight));
        points.addAll(drawHorizontalLine(lowerLeft, lowerRight));
        points.addAll(drawVerticalLine(upperLeft, lowerLeft));
        points.addAll(drawVerticalLine(upperRight, lowerRight));

        return points;
    }

    public static RectangleStrategy create(Point upperLeft, Point lowerRight) {
        return new RectangleStrategy(upperLeft, lowerRight);
    }


}
