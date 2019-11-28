package com.zuhlke.paint.ascii.figure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTests  {

    @Test
    void createPoint() {
        assertNotNull(Point.create(1, 2));
    }

    @Test
    void getX() {
        int x = 3;
        int y = 4;
        Point point = Point.create(x, y);
        assertEquals(x, point.getX());
    }

    @Test
    void getY() {
        int x = 5;
        int y = 7;
        Point point = Point.create(x, y);
        assertEquals(y, point.getY());
    }

    @Test
    void customEquals() {
        Point point = Point.create(3, 5);
        Point samePoint = Point.create(3, 5);
        Point differentPoint = Point.create(4, 6);
        Point similarPoint = Point.create(3, 6);

        assertTrue(point.equals(samePoint));
        assertFalse(point.equals(differentPoint));
        assertFalse(point.equals(similarPoint));
        assertFalse(point.equals(null));
        assertTrue(point.equals(point));
        assertFalse(point.equals(new Object()));

//        assertEquals(point, samePoint);
//        assertNotEquals(point, differentPoint);
//        assertNotEquals(nullPoint, point);
//        assertEquals(point, point);
//        assertNotEquals(point, new Object());
    }

    @Test
    void customHashCode() {
        Point point = Point.create(3, 5);
        Point anotherPoint = Point.create(4, 6);
        assertNotEquals(point.hashCode(), anotherPoint.hashCode());
        Point yetAnotherPoint = Point.create(3, 5);
        assertEquals(point.hashCode(), yetAnotherPoint.hashCode());
    }
}