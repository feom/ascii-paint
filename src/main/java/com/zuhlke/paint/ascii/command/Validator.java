package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.AsciiPaintException;

import java.util.List;

/**
 * The {@code Validator} class provides validation logic methods.
 * It can be used for command validations.
 */
public class Validator {

    private Validator() {
    }

    public static Validator create() {
        return new Validator();
    }

    public void validateStrictlyPositive(int... numbers) throws AsciiPaintException {
        for(int number : numbers) {
            if (number <= 0) {
                throw new AsciiPaintException("Parameters must be greater zero!");
            }
        }
    }

    public void validateCoordinatePairs(int x1, int y1, int x2, int y2) throws AsciiPaintException {
        if (x1 > x2 || y1 > y2) {
            throw new AsciiPaintException("Second coordinate pair should be greater than first one!");
        }
    }

    public void validateNumberOfArguments(List<String> arguments, int number) throws AsciiPaintException {
        if (arguments == null || arguments.size() != number) {
            throw new AsciiPaintException("Invalid number of arguments for command!");
        }
    }

}
