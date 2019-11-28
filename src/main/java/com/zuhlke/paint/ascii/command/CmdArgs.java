package com.zuhlke.paint.ascii.command;

/**
 * The {@code CmdArgs} represents all available command arguments.
 */
public enum CmdArgs {

    X1("X1"),
    X2("X2"),
    Y1("Y1"),
    Y2("Y2"),
    W("WIDTH"),
    H("HEIGHT"),
    C("COLOUR");

    private final String commandArgName;

    CmdArgs(String commandArg) {
        this.commandArgName = commandArg;
    }

}
