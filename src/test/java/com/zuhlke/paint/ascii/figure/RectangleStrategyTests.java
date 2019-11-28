package com.zuhlke.paint.ascii.figure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleStrategyTests  {

    @Test
    void create() {
        Point upperLeft = Point.create(1, 2);
        Point lowerRight = Point.create(4, 4);
        assertNotNull(RectangleStrategy.create(upperLeft, lowerRight));
    }

    @Test
    void drawRectangle() {
        Point upperLeft = Point.create(1, 2);
        Point lowerRight = Point.create(4, 4);
        RectangleStrategy rectangleStrategy= RectangleStrategy.create(upperLeft, lowerRight);
        int numberOfExpectedPoints = 2 * (lowerRight.getX() - upperLeft.getX() + 1);
        numberOfExpectedPoints += (2 * (lowerRight.getY() - upperLeft.getY() + 1));

        List<Point> pointList = rectangleStrategy.draw();

        // the number points drawn must match the expected ones above:
        assertEquals(numberOfExpectedPoints, pointList.size());
    }
}
