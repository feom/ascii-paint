package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.figure.Point;
import com.zuhlke.paint.ascii.figure.Renderable;

import java.util.List;

import static com.zuhlke.paint.ascii.command.CmdArgs.*;

/**
 * The {@code FillCommand} class represents a bucket fill command.
 * It accepts x,y coordinates as well as a fill colour.
 */
public class FillCommand extends CommandBase {

    public FillCommand(List<String> arguments, ApplicationContext context) throws AsciiPaintException {
        super(arguments, context);
    }

    @Override
    protected void extractArguments() {
        cmdArgsMap.put(X1, arguments.get(1));
        cmdArgsMap.put(Y1, arguments.get(2));
        cmdArgsMap.put(C, arguments.get(3));
    }

    @Override
    protected int getExpectedCommandLength() {
        return 4;
    }

    @Override
    public void validate() throws AsciiPaintException {
        hasRenderable();
        validator.validateStrictlyPositive(getArg(X1), getArg(Y1));
        validateColour();
    }

    protected void validateColour() throws AsciiPaintException {
        String colour = cmdArgsMap.get(C);
        if (colour.length() != 1) {
            throw new AsciiPaintException("Colour must be a single char!");
        }
    }

    @Override
    public void doExecute() throws AsciiPaintException {
        Point startPoint = Point.create(getArg(X1), getArg(Y1));
        Renderable currentRenderable = applicationContext.getCurrentRenderable();
        currentRenderable.bucketFill(startPoint, cmdArgsMap.get(C).charAt(0));
        applicationContext.update();
    }
}
