package com.zuhlke.paint.ascii.figure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The {@code Canvas} class represents a two dimensional {@code Renderable}.
 * It is aware of its boundaries and allows to draw inside its boundaries.
 * Points are stored in a sparse way in a {@code Map}.
 * Only during rendering, the full container including empty points is built.
 */
public class Canvas implements Renderable {

    private int width;
    private int height;
    private Map<Point, Character> canvasMap;
    private Character emptyColour;

    private Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.canvasMap = new HashMap<>();
        this.emptyColour = ' ';
    }

    @Override
    public void bucketFill(Point startPoint, char colour) {
        if (!isInBounds(startPoint)) {
            return;
        }
        Character startColour = getColour(startPoint);
        doFill(startPoint, startColour, colour);
    }

    /**
     * Recursive bucket fill algorithm:
     * From the current point traverse left, right, up and down and fill
     * with target colour as long as current point is in bounds,
     * matches the start colour and is not already filled with target colour.
     *
     * @param currentPoint the current point
     * @param startColour the start colour
     * @param targetColour the target colour
     *
     */
    private void doFill(Point currentPoint, char startColour, char targetColour) {
        Character currentColour = getColour(currentPoint);

        if (!isInBounds(currentPoint)) {
            return;
        }

        if (!currentColour.equals(startColour)) {
            return;
        }

        if (currentColour.equals(targetColour)) {
            return;
        }

        canvasMap.put(currentPoint, targetColour);

        Point left = Point.create(currentPoint.getX() - 1, currentPoint.getY());
        doFill(left, startColour, targetColour);
        Point right = Point.create(currentPoint.getX() + 1, currentPoint.getY());
        doFill(right, startColour, targetColour);
        Point up = Point.create(currentPoint.getX(), currentPoint.getY() - 1);
        doFill(up, startColour, targetColour);
        Point down = Point.create(currentPoint.getX(), currentPoint.getY() + 1);
        doFill(down, startColour, targetColour);
    }

    private boolean isInBounds(Point point) {
        return point.getX() >= 1 && point.getX() <= width && point.getY() >= 1 && point.getY() <= height;
    }

    private Character getColour(Point point) {
        return canvasMap.getOrDefault(point, emptyColour);
    }

    @Override
    public void drawPoints(List<Point> points, Pen pen) {
        points.stream()
                .filter(this::isInBounds)
                .forEach((p) -> canvasMap.put(p, pen.getPen()));
    }

    @Override
    public List<String> render() {
        List<String> result = new ArrayList<>();

        for (int h = 1; h <= height; h++) {
            StringBuilder sb = new StringBuilder();
            for (int w = 1; w <= width; w++) {
                Character character = canvasMap.getOrDefault(Point.create(w, h), emptyColour);
                sb.append(character);
            }
            result.add(sb.toString());
        }

        return result;
    }

    @Override
    public void render(Consumer<? super String> action) {
        render().forEach(action);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getNumberOfPoints() {
        return canvasMap.size();
    }

    public static Canvas create(int width, int height) {
        return new Canvas(width, height);
    }


}
