package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.figure.Pen;
import com.zuhlke.paint.ascii.figure.Point;
import com.zuhlke.paint.ascii.figure.Renderable;
import com.zuhlke.paint.ascii.figure.ShapeStrategy;

import java.util.List;

import static com.zuhlke.paint.ascii.command.CmdArgs.*;

/**
 * The {@code ShapeCommand} class represents line and rectangle draw commands.
 * It can create and draw lines and rectangles on a {@code Renderable}.
 * It accepts two ordered coordinate pairs (x1/2, y1/y2) as input arguments.
 */
public class ShapeCommand extends CommandBase {

    public ShapeCommand(List<String> arguments, ApplicationContext context) throws AsciiPaintException {
        super(arguments, context);
    }

    @Override
    protected void extractArguments() {
        cmdArgsMap.put(X1, arguments.get(1));
        cmdArgsMap.put(Y1, arguments.get(2));
        cmdArgsMap.put(X2, arguments.get(3));
        cmdArgsMap.put(Y2, arguments.get(4));
    }

    @Override
    protected int getExpectedCommandLength() {
        return 5;
    }

    @Override
    public void validate() throws AsciiPaintException {
        hasRenderable();
        validator.validateStrictlyPositive(getArg(X1), getArg(Y1), getArg(X2), getArg(Y2));
        validator.validateCoordinatePairs(getArg(X1), getArg(Y1), getArg(X2), getArg(Y2));
    }

    @Override
    public void doExecute() throws AsciiPaintException {
        Point first = Point.create(getArg(X1), getArg(Y1));
        Point second = Point.create(getArg(X2), getArg(Y2));
        CommandCode commandCode = getCommandCode(arguments.get(0));

        ShapeStrategy shape = applicationContext.getShapeStrategyFactory().createShapeStrategy(commandCode, first, second);
        Renderable currentRenderable = applicationContext.getCurrentRenderable();
        currentRenderable.drawPoints(shape.draw(), Pen.PEN_DEFAULT);
        applicationContext.update();
    }
}
