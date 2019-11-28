package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.AsciiPaintException;

/**
 * A {@code Command} represents a command of the ascii-paint application.
 * A command can be executed and validated.
 * It should be ensured a command is always validated before execution.
 */
public interface Command {

    /**
     * Validate the command and throw if validation fails.
     *
     * @throws AsciiPaintException
     */
    void validate() throws AsciiPaintException;

    /**
     * Execute the command and throw if execution fails.
     *
     * @throws AsciiPaintException
     */
    void execute() throws AsciiPaintException;

}
