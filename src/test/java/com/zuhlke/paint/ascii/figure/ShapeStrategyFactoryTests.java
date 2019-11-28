package com.zuhlke.paint.ascii.figure;

import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.command.CommandCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeStrategyFactoryTests {

    private ShapeStrategyFactory shapeStrategyFactory;

    @BeforeEach
    public void beforeEachTest() {
        shapeStrategyFactory = ShapeStrategyFactoryImpl.create();
    }

    @Test
    void create() {
        assertNotNull(shapeStrategyFactory);
    }

    @Test
    void createLineStrategy() throws AsciiPaintException {
        ShapeStrategy shapeStrategy = shapeStrategyFactory.createShapeStrategy(CommandCode.L, Point.create(1, 3), Point.create(1, 7));
        assertTrue(shapeStrategy instanceof LineStrategy);
    }

    @Test
    void createRectangleStrategy() throws AsciiPaintException {
        ShapeStrategy shapeStrategy = shapeStrategyFactory.createShapeStrategy(CommandCode.R, Point.create(1, 1), Point.create(3, 4));
        assertTrue(shapeStrategy instanceof RectangleStrategy);
    }

    @Test
    void createUnknownStrategy() {
        assertThrows(AsciiPaintException.class, () ->
                shapeStrategyFactory.createShapeStrategy(CommandCode.Q, Point.create(1, 1), Point.create(3, 4)));

    }
}