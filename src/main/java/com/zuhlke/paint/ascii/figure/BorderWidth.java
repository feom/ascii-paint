package com.zuhlke.paint.ascii.figure;

/**
 * The {@code BorderWidth} represents the border width used by decorators.
 */
public enum BorderWidth {

    THIN (1);

    private final int borderWidth;

    BorderWidth(int levelCode) {
        this.borderWidth = levelCode;
    }

    public int getBorderWidth() {
        return borderWidth;
    }
}
