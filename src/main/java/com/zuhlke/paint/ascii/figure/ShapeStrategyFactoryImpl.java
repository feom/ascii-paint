package com.zuhlke.paint.ascii.figure;

import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.CreateFunction;
import com.zuhlke.paint.ascii.command.CommandCode;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ShapeStrategyFactoryImpl} class represents an implementation of {@code ShapeStrategyFactory}.
 */
public class ShapeStrategyFactoryImpl implements ShapeStrategyFactory {

    private Map<CommandCode, CreateFunction<Point, Point, ShapeStrategy>> commandMap;

    private ShapeStrategyFactoryImpl() {
        initCommandMap();
    }

    private void initCommandMap() {
        commandMap = new HashMap<>();
        commandMap.put(CommandCode.L, LineStrategy::create);
        commandMap.put(CommandCode.R, RectangleStrategy::create);
    }

    public static ShapeStrategyFactory create() {
        return new ShapeStrategyFactoryImpl();
    }

    @Override
    public ShapeStrategy createShapeStrategy(CommandCode commandCode, Point first, Point second) throws AsciiPaintException {
        CreateFunction<Point, Point, ShapeStrategy> createFunction = commandMap.get(commandCode);
        if (createFunction == null) {
            throw new AsciiPaintException("Unknown shape strategy!");
        } else {
            return createFunction.create(first, second);
        }

    }


}
