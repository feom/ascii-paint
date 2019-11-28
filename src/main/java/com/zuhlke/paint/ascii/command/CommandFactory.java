package com.zuhlke.paint.ascii.command;

import com.zuhlke.paint.ascii.AsciiPaintException;

import java.util.List;

/**
 * A {@code CommandFactory} creates ascii-paint commands.
 */
public interface CommandFactory {

    /**
     * Create a command using the given input (e.g. entered by the user).
     *
     * @param arguments the command input
     * @return the command
     * @throws AsciiPaintException
     */
    Command createCommand(List<String> arguments) throws AsciiPaintException;
}
