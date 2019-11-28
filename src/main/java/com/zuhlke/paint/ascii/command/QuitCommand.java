package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;

import java.util.List;

/**
 * The {@code QuitCommand} class represents a quit command.
 * It allows to exit the ascii-paint application.
 */
public class QuitCommand extends CommandBase {

    public QuitCommand(List<String> arguments, ApplicationContext context) throws AsciiPaintException {
        super(arguments, context);
    }

    @Override
    protected void extractArguments() {
        // quit has no arguments to extract
    }

    @Override
    protected int getExpectedCommandLength() {
        return 1;
    }

    @Override
    public void validate() {
        // quit has no arguments to validate
    }

    @Override
    public void doExecute() {
        applicationContext.setExecuting(false);
    }
}
