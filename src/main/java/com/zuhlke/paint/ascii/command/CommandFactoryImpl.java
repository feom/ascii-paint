package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;

import java.util.List;

/**
 * The {@code CommandFactoryImpl} class represents an implementation of {@code CommandFactory}.
 * It can create commands for all codes available in {@code CommandCode}.
 */
public class CommandFactoryImpl implements CommandFactory {

    private ApplicationContext applicationContext;

    private CommandFactoryImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static CommandFactory create(ApplicationContext applicationContext) {
        return new CommandFactoryImpl(applicationContext);
    }

    public Command createCommand(List<String> arguments) throws AsciiPaintException {
        if (arguments == null || arguments.isEmpty()) {
            throw new AsciiPaintException("Empty command!");
        }

        CommandCode commandCode = CommandCode.fromString(arguments.get(0));

        if (commandCode.equals(CommandCode.C)) {
            return new CanvasCommand(arguments, applicationContext);
        }
        else if (commandCode.equals(CommandCode.L)) {
            return new ShapeCommand(arguments, applicationContext);
        }
        else if (commandCode.equals(CommandCode.R)) {
            return new ShapeCommand(arguments, applicationContext);
        }
        else if (commandCode.equals(CommandCode.B)) {
            return new FillCommand(arguments, applicationContext);
        }
        else if (commandCode.equals(CommandCode.Q)) {
            return new QuitCommand(arguments, applicationContext);
        }
        else {
            throw new AsciiPaintException("Unknown command code!");
        }

    }

}
