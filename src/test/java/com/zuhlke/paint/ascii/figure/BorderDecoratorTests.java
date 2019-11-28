package com.zuhlke.paint.ascii.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class BorderDecoratorTests {

    private Renderable borderDecorator;
    private Renderable canvas;
    private int width = 10;
    private int height = 10;
    private BorderWidth borderWidth = BorderWidth.THIN;

    @BeforeEach
    public void beforeEachTest() {
        canvas = Canvas.create(width, height);
        borderDecorator = BorderDecorator.create(canvas, borderWidth);
    }

    @Test
    void create() {
        assertNotNull(borderDecorator);
    }

    @Test
    void getWidth() {
        assertEquals(width + 2 * borderWidth.getBorderWidth(), borderDecorator.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height + 2 * borderWidth.getBorderWidth(), borderDecorator.getHeight());
    }

    @Test
    void renderToText() {
        Point startPoint = Point.create(4, 1);
        char colour = 'c';
        List<Point> somePoints = new ArrayList<>();
        somePoints.add(startPoint);

        borderDecorator.drawPoints(somePoints, Pen.PEN_DEFAULT);
        borderDecorator.bucketFill(startPoint, colour);
        List<String> renderResult = borderDecorator.render();

        // the number of lines must be the no lines of the decorated canvas + 2 * border width:
        assertEquals(height + 2 * borderWidth.getBorderWidth(), renderResult.size());
        // each line must have length of width of decorated canvas + 2 * border width:
        renderResult.forEach((line) -> assertEquals(width + 2 * borderWidth.getBorderWidth(), line.length()));
    }

    @Test
    void renderToConsumer() {
        borderDecorator.render(Assertions::assertNotNull);
    }

    @Test
    void getNumberOfPoints() {
        // the border decorator does not create new points in the canvas
        assertEquals(canvas.getNumberOfPoints(), borderDecorator.getNumberOfPoints());
    }

}