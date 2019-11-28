package com.zuhlke.paint.ascii.figure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LineStrategyTests {

    @Test
    void create() {
        Point start = Point.create(1, 2);
        Point end = Point.create(1, 8);
        assertNotNull(LineStrategy.create(start, end));
    }

    @Test
    void drawVerticalLine() {
        Point start = Point.create(1, 2);
        Point end = Point.create(1, 4);

        LineStrategy lineStrategy = LineStrategy.create(start, end);
        List<Point> pointList = lineStrategy.draw();

        assertEquals(end.getY() - start.getY() + 1, pointList.size());
    }

    @Test
    void drawHorizontalLine() {
        Point start = Point.create(2, 2);
        Point end = Point.create(4, 2);

        LineStrategy lineStrategy = LineStrategy.create(start, end);
        List<Point> pointList = lineStrategy.draw();

        assertEquals(end.getX() - start.getX() + 1, pointList.size());
    }

    @Test
    void drawCurvedLine() {
        Point start = Point.create(2, 3);
        Point end = Point.create(5, 7);

        LineStrategy lineStrategy = LineStrategy.create(start, end);
        List<Point> pointList = lineStrategy.draw();

        // Curved lines are not supported - thus nothing shall be drawn in this case
        assertEquals(0, pointList.size());
    }


}