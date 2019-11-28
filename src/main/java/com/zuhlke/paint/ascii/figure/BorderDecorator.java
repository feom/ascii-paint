package com.zuhlke.paint.ascii.figure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The {@code BorderDecorator} class represents a decorator for a two dimensional {@code Renderable}.
 * It can decorate the given {@code Renderable} with a border of a specific width.
 * Currently is uses default characters for horizontal and vertical {@code BorderStyle}.
 * Currently is uses per default thin {@code BorderStyle}.
 */
public class BorderDecorator implements Renderable {
    private Renderable decoratedRenderable;
    private int totalWidth;
    private int totalHeight;
    private BorderWidth borderWidth;

    private BorderDecorator(Renderable decoratedRenderable, BorderWidth borderWidth) {
        this.decoratedRenderable = decoratedRenderable;
        this.borderWidth = borderWidth;
        this.totalWidth = decoratedRenderable.getWidth() + 2 * borderWidth.getBorderWidth();
        this.totalHeight = decoratedRenderable.getHeight() + 2 * borderWidth.getBorderWidth();
    }

    public static BorderDecorator create(Renderable decoratedRenderable, BorderWidth borderWidth) {
        return new BorderDecorator(decoratedRenderable, borderWidth);
    }

    @Override
    public List<String> render() {
        List<String> decoRenderResult = decoratedRenderable.render();

        List<String> result = new ArrayList<>();
        appendHorizontalBorders(result);

        for (String str : decoRenderResult) {
            result.add(appendVerticalBorders(str));
        }

        appendHorizontalBorders(result);

        return result;
    }

    @Override
    public void render(Consumer<? super String> action) {
        render().forEach(action);
    }

    @Override
    public void drawPoints(List<Point> points, Pen pen) {
        decoratedRenderable.drawPoints(points, pen);
    }

    @Override
    public void bucketFill(Point startPoint, char colour) {
        decoratedRenderable.bucketFill(startPoint, colour);
    }

    @Override
    public int getWidth() {
        return totalWidth;
    }

    @Override
    public int getHeight() {
        return totalHeight;
    }

    @Override
    public int getNumberOfPoints() {
        return decoratedRenderable.getNumberOfPoints();
    }

    private void appendHorizontalBorders(List<String> input) {
        for (int i = 0; i < borderWidth.getBorderWidth(); i++ ) {
            input.add(getHorizontalLine());
        }
    }

    private String appendVerticalBorders(String input) {
        String border = repeatInput(String.valueOf(BorderStyle.VERTICAL_DEFAULT.getBorderStyle()), borderWidth.getBorderWidth());
        return border + input + border;
    }

    private String repeatInput(String input, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(input);
        }
        return sb.toString();
    }

    private String getHorizontalLine() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= totalWidth; i++) {
            sb.append(BorderStyle.HORIZONTAL_DEFAULT.getBorderStyle());
        }
        return sb.toString();
    }

}
