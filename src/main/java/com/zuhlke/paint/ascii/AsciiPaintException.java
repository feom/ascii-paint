package com.zuhlke.paint.ascii;

/**
 * The {@code AsciiPaintException} is thrown by the ascii-paint application.
 */
public class AsciiPaintException extends Exception {

    public AsciiPaintException(String message) {
        super(message);
    }

    public AsciiPaintException(String message, Throwable cause) {
        super(message, cause);
    }
}
