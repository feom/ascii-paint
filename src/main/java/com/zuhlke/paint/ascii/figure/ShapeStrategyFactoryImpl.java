package com.zuhlke.paint.ascii.figure;

import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.command.CommandCode;

/**
 * The {@code ShapeStrategyFactoryImpl} class represents an implementation of {@code ShapeStrategyFactory}.
 */
public class ShapeStrategyFactoryImpl implements ShapeStrategyFactory {

    private ShapeStrategyFactoryImpl() {
    }

    public static ShapeStrategyFactory create() {
        return new ShapeStrategyFactoryImpl();
    }

    @Override
    public ShapeStrategy createShapeStrategy(CommandCode commandCode, Point first, Point second) throws AsciiPaintException {

        if (commandCode.equals(CommandCode.L)) {
            return LineStrategy.create(first, second);
        }
        else if (commandCode.equals(CommandCode.R)) {
            return RectangleStrategy.create(first, second);
        }
        else {
            throw new AsciiPaintException("Unknown shape strategy!");
        }
    }


}
