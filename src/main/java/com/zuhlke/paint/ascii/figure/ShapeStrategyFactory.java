package com.zuhlke.paint.ascii.figure;

import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.command.CommandCode;

/**
 * A {@code ShapeStrategyFactory} creates shape strategies.
 */
public interface ShapeStrategyFactory {


    /**
     * Create a shape strategy according to the given command code.
     *
     * @param commandCode the command code determining the shape strategy
     * @param first the first point of the shape
     * @param second the second point of the shape
     * @return the shape strategy
     * @throws AsciiPaintException
     */
    ShapeStrategy createShapeStrategy(CommandCode commandCode, Point first, Point second) throws AsciiPaintException;
}
