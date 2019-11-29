package com.zuhlke.paint.ascii.figure;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point point, Point t1) {
        return point.getX() - t1.getX();
    }
}
