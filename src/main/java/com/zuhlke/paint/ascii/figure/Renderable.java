package com.zuhlke.paint.ascii.figure;

import java.util.List;
import java.util.function.Consumer;

/**
 * A {@code Renderable} represents a container
 * unto which points of e.g. shapes can be drawn.
 * Its contents can be rendered to text (list of strings)
 * and can be consumed by a {@code Consumer}.
 *
 */
public interface Renderable {

    /**
     * Render the contents to a list of strings.
     *
     * @return the contents as a list of strings
     */
    List<String> render();

    /**
     * Render the contents to a {@code Consumer}.
     *
     * @param action the consumer
     */
    void render(Consumer<? super String> action);

    /**
     * Draws the given list of points using the given {@code Pen}.
     *
     * @param points the list of points to draw
     * @param pen the pen to use
     */
    void drawPoints(List<Point> points, Pen pen);

    /**
     * Fills with the given colour according to
     * the bucket fill algorithm of ms paint application.
     *  @param startPoint the start point
     * @param colour the colour to use
     */
    void bucketFill(Point startPoint, Character colour);

    /**
     * The width of this container.
     *
     * @return the width
     */
    int getWidth();

    /**
     * The height of this container.
     *
     * @return the height
     */
    int getHeight();

    /**
     * The current number of points on the container.
     *
     * @return current number of points
     */
    int getNumberOfPoints();

}
