package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.ApplicationContext;
import com.zuhlke.paint.ascii.AsciiPaintException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code CommandBase} class represents a base class for commands.
 * Since arguments in ascii-paint are positional and unnamed it delegates argument extraction to concrete command subclasses.
 * Using template methods, it ensures a command is validated before execution.
 *
 */
public abstract class CommandBase implements Command {

    protected List<String> arguments;
    protected Map<CmdArgs, String> cmdArgsMap;
    protected ApplicationContext applicationContext;
    protected Validator validator;

    protected CommandBase(List<String> arguments, ApplicationContext context) throws AsciiPaintException {
        this.arguments = arguments;
        this.applicationContext = context;
        this.validator = Validator.create();
        validator.validateNumberOfArguments(this.arguments, getExpectedCommandLength());
        this.cmdArgsMap = new HashMap<>();
        extractArguments();
    }

    /**
     * Extract the positional, unnamed arguments of the command.
     * Intended to be implemented by concrete command subclasses.
     *
     * @throws AsciiPaintException
     */
    protected abstract void extractArguments() throws AsciiPaintException;

    /**
     * Return the expected total command length including command code and unnamed arguments.
     * Intended to be implemented by concrete command subclasses.
     *
     * @return the total command length
     */
    protected abstract int getExpectedCommandLength();

    protected int getArg(CmdArgs arg) throws AsciiPaintException {
        return parse(cmdArgsMap.get(arg));
    }

    /**
     * The command specific execution logic.
     * Intended to be implemented by concrete command subclasses.
     *
     * @throws AsciiPaintException
     */
    protected abstract void doExecute() throws AsciiPaintException;

    @Override
    public void execute() throws AsciiPaintException {
          validate();
          doExecute();
    }

    protected CommandCode getCommandCode(String inputCode) {
        return CommandCode.fromString(inputCode);
    }

    protected int parse(String parameter) throws AsciiPaintException {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new AsciiPaintException("Non numeric input provided - " + e.getLocalizedMessage(), e);
        }
    }

    protected void hasRenderable() throws AsciiPaintException {
        if (applicationContext.getCurrentRenderable() == null) {
            throw new AsciiPaintException("Please create a canvas first before drawing!");
        }
    }

}
