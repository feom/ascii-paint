package com.zuhlke.paint.ascii.figure;

/**
 * The {@code Pen} represents the character ("pen") used to draw.
 */
public enum Pen {

    PEN_DEFAULT('x');

    private final char pen;

    Pen(char pen) {
        this.pen = pen;
    }

    public char getPen() {
        return pen;
    }
}
