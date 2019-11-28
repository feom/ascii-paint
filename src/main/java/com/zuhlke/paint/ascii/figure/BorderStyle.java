package com.zuhlke.paint.ascii.figure;

/**
 * The {@code BorderStyle} represents the horizontal and vertical border styles used by decorators.
 */
public enum BorderStyle {

    VERTICAL_DEFAULT('|'),
    HORIZONTAL_DEFAULT('-');

    private final char borderStyle;

    BorderStyle(char borderStyle) {
        this.borderStyle = borderStyle;
    }

    public char getBorderStyle() {
        return borderStyle;
    }
}
