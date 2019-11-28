package com.zuhlke.paint.ascii.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CanvasTests {

    private Renderable canvas;
    private int width = 10;
    private int height = 10;

    @BeforeEach
    public void beforeEachTest() {
        canvas = Canvas.create(width, height);
    }

    @Test
    void create() {
        assertNotNull(canvas);
    }

    @Test
    void getWidth() {
        assertEquals(width, canvas.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height, canvas.getHeight());
    }

    @Test
    void drawPointsInsideCanvas() {
        List<Point> somePointsInside = new ArrayList<>();
        somePointsInside.add(Point.create(4, 3));

        canvas.drawPoints(somePointsInside, Pen.PEN_DEFAULT);
        assertEquals(1, canvas.getNumberOfPoints());
    }

    @Test
    void drawPointsOutsideCanvas() {
        List<Point> somePointsOutside = new ArrayList<>();
        somePointsOutside.add(Point.create(11, 11));

        canvas.drawPoints(somePointsOutside, Pen.PEN_DEFAULT);

        assertEquals(0, canvas.getNumberOfPoints());
    }

    @Test
    void drawPointsPartiallyInsideCanvas() {
        List<Point> somePointsInsideAndOutside = new ArrayList<>();
        somePointsInsideAndOutside.add(Point.create(11, 11));
        somePointsInsideAndOutside.add(Point.create(2, 3));

        canvas.drawPoints(somePointsInsideAndOutside, Pen.PEN_DEFAULT);

        assertEquals(1, canvas.getNumberOfPoints());
    }

    private List<String> renderTestCanvas() {
        List<Point> somePoints = new ArrayList<>();
        somePoints.add(Point.create(4, 3));
        canvas.drawPoints(somePoints, Pen.PEN_DEFAULT);
        return canvas.render();
    }

    @Test
    void renderToText() {
        List<String> renderResult = renderTestCanvas();

        // there must be height number of lines:
        assertEquals(height, renderResult.size());
        // each line must have length of width:
        renderResult.forEach((line) -> assertEquals(width, line.length()));
    }

    @Test
    void renderToTextWithPenChecked() {
        List<String> renderResult = renderTestCanvas();

        // after removing empty characters there must be exactly one character left with pen default:
        StringBuilder sb = new StringBuilder();
        renderResult.forEach(sb::append);
        assertEquals(String.valueOf(Pen.PEN_DEFAULT.getPen()), sb.toString().trim());
    }


    @Test
    void renderToConsumer() {
        canvas.render(Assertions::assertNotNull);
    }

    @Test
    void bucketFillWithEmptyCanvas() {
        Point startPoint = Point.create(4, 1);
        char colour = 'c';

        canvas.bucketFill(startPoint, colour);
        List<String> renderResult = canvas.render();
        // each char of the resulting text must be the same as colour:
        renderResult.forEach(line -> {
                    assertTrue(line.chars().allMatch(c -> c == colour));
                }
        );
    }

    @Test
    void bucketFillWithNonEmptyCanvasAndStartInBlankPoint() {
        Point startPoint = Point.create(4, 1);
        char colour = 'c';

        List<Point> somePoints = new ArrayList<>();
        somePoints.add(Point.create(4, 3));

        canvas.drawPoints(somePoints, Pen.PEN_DEFAULT);
        canvas.bucketFill(startPoint, colour);
        List<String> renderResult = canvas.render();

        // each char of the result must equal colour except the one point drawn:
        String resultText = listToString(renderResult);
        assertEquals(1, resultText.chars().filter(c -> c != colour).count());
    }

    private String listToString(List<String> renderResult) {
        StringBuilder sb = new StringBuilder();
        renderResult.forEach(sb::append);
        return sb.toString();
    }

    @Test
    void bucketFillWithNonEmptyCanvasAndStartInFilledPoint() {
        Point startPoint = Point.create(1, 1);
        char colour = 'c';

        List<Point> somePoints = new ArrayList<>();
        somePoints.add(Point.create(1, 1));
        somePoints.add(Point.create(1, 2));

        canvas.drawPoints(somePoints, Pen.PEN_DEFAULT);
        canvas.bucketFill(startPoint, colour);
        List<String> renderResult = canvas.render();

        // the result must have exactly two points with matching colour:
        String resultText = listToString(renderResult);
        assertEquals(2, resultText.chars().filter(c -> c == colour).count());
    }

    @Test
    void bucketFillWithOutOfBoundsStartPoint() {
        Point startPoint = Point.create(20, 20);
        char colour = 'c';

        List<String> renderResultBefore = canvas.render();
        canvas.bucketFill(startPoint, colour);
        List<String> renderResultAfter = canvas.render();

        // in case the starting point is out of bounds bucket fill should not change canvas:
        assertTrue(renderResultBefore.stream().allMatch(line -> renderResultAfter.contains(line)));
    }

}