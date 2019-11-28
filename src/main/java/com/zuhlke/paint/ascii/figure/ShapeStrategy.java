package com.zuhlke.paint.ascii.figure;

import java.util.List;

/**
 * A {@code ShapeStrategy} represents a strategy (algorithm) according to which a shape is drawn.
 *
 */
public interface ShapeStrategy {

    /**
     * Draw the shape by producing a list of points.
     *
     * @return the resuting list of points
     */
    List<Point> draw();
}
