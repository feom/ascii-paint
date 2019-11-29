package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.figure.BorderDecorator;
import com.zuhlke.paint.ascii.figure.BorderWidth;
import com.zuhlke.paint.ascii.figure.Canvas;
import com.zuhlke.paint.ascii.figure.Renderable;

import java.util.List;

import static com.zuhlke.paint.ascii.command.CmdArgs.H;
import static com.zuhlke.paint.ascii.command.CmdArgs.W;

/**
 * The {@code CanvasCommand} class represents a command to create a new canvas.
 * It accepts width and height arguments for the canvas.
 */
public class CanvasCommand extends CommandBase {

    public CanvasCommand(List<String> arguments, ApplicationContext context) throws AsciiPaintException {
        super(arguments, context);
    }

    @Override
    protected void extractArguments() {
        cmdArgsMap.put(W, arguments.get(1));
        cmdArgsMap.put(H, arguments.get(2));
    }

    @Override
    protected int getExpectedCommandLength() {
        return 3;
    }

    @Override
    public void validate() throws AsciiPaintException {
        validator.validateStrictlyPositive(getArg(W), getArg(H));
    }

    @Override
    protected void doExecute() throws AsciiPaintException {
        Renderable canvas = Canvas.create(getArg(W), getArg(H));
        Renderable canvasWithBorder = BorderDecorator.create(canvas, BorderWidth.THIN);

        applicationContext.setCurrentRenderable(canvasWithBorder);
        applicationContext.update();
    }


}
