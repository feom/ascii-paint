package com.zuhlke.paint.ascii.figure;

import java.util.*;
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


    /**
     * Breadth-first-search bucket (flood) fill algorithm using a queue to store points.
     * @param startPoint the start point
     * @param targetColour the colour to fill with
     */
    @Override
    public void bucketFill(Point startPoint, Character targetColour) {
        Character startColour = getColour(startPoint);
        if (isOutOfBounds(startPoint)) {
            return;
        }

        if (startColour.equals(targetColour)) {
            return;
        }

        canvasMap.put(startPoint, targetColour);
        Queue<Point> queue = new PriorityQueue<>(new PointComparator());
        queue.add(startPoint);
        while (!queue.isEmpty()) {
            Point point = queue.remove();

            Point left = Point.create(point.getX() - 1, point.getY());
            handlePoint(queue, left, startColour, targetColour);
            Point right = Point.create(point.getX() + 1, point.getY());
            handlePoint(queue, right, startColour, targetColour);
            Point up = Point.create(point.getX(), point.getY() - 1);
            handlePoint(queue, up, startColour, targetColour);
            Point down = Point.create(point.getX(), point.getY() + 1);
            handlePoint(queue, down, startColour, targetColour);
        }
    }

    private void handlePoint(Queue<Point> queue, Point point, Character startColour, Character targetColour) {
        if (isOutOfBounds(point)) {
            return;
        }
        if (getColour(point).equals(startColour)) {
            canvasMap.put(point, targetColour);
            queue.add(point);
        }
    }

    private boolean isInBounds(Point point) {
        return point.getX() >= 1 && point.getX() <= width && point.getY() >= 1 && point.getY() <= height;
    }

    private boolean isOutOfBounds(Point point) {
        return !isInBounds(point);
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
