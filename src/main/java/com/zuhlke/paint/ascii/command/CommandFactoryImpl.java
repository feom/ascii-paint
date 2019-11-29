package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;
import com.zuhlke.paint.ascii.CreateFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code CommandFactoryImpl} class represents an implementation of {@code CommandFactory}.
 * It can create commands for all codes available in {@code CommandCode}.
 */
public class CommandFactoryImpl implements CommandFactory {

    private ApplicationContext applicationContext;
    private Map<CommandCode, CreateFunction<List<String>, ApplicationContext, Command>> commandMap;

    private CommandFactoryImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        initCommandMap();
    }

    private void initCommandMap() {
        commandMap = new HashMap<>();
        commandMap.put(CommandCode.C, CanvasCommand::new);
        commandMap.put(CommandCode.L, ShapeCommand::new);
        commandMap.put(CommandCode.R, ShapeCommand::new);
        commandMap.put(CommandCode.B, FillCommand::new);
        commandMap.put(CommandCode.Q, QuitCommand::new);
    }

    public static CommandFactory create(ApplicationContext applicationContext) {
        return new CommandFactoryImpl(applicationContext);
    }

    public Command createCommand(List<String> arguments) throws AsciiPaintException {
        if (arguments == null || arguments.isEmpty()) {
            throw new AsciiPaintException("Empty command!");
        }

        CommandCode commandCode = CommandCode.fromString(arguments.get(0));
        CreateFunction<List<String>, ApplicationContext, Command> createFunction = commandMap.get(commandCode);
        if (createFunction == null) {
            throw new AsciiPaintException("Unknown command code!");
        } else {
            return createFunction.create(arguments, applicationContext);
        }

//        if (commandCode.equals(CommandCode.C)) {
//            return new CanvasCommand(arguments, applicationContext);
//        } else if (commandCode.equals(CommandCode.L)) {
//            return new ShapeCommand(arguments, applicationContext);
//        } else if (commandCode.equals(CommandCode.R)) {
//            return new ShapeCommand(arguments, applicationContext);
//        } else if (commandCode.equals(CommandCode.B)) {
//            return new FillCommand(arguments, applicationContext);
//        } else if (commandCode.equals(CommandCode.Q)) {
//            return new QuitCommand(arguments, applicationContext);
//        } else {
//        }

    }

}
